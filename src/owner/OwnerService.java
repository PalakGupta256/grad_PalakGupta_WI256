package owner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import model.User;
import util.DBConnection;

public class OwnerService {

    // 1️⃣ View my site
    public void viewMySite(User user) {
        String sql = "SELECT s.site_id, s.site_type, s.length, s.width, s.sqft, s.is_occupied, s.maintenance_rate " +
                     "FROM site s " +
                     "JOIN owner o ON s.site_id = o.site_id " +
                     "WHERE o.user_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, user.getUserId());
            ResultSet rs = pst.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("No site found for you.");
                return;
            }

            System.out.println("\n--- MY SITE ---");
            System.out.printf("%-5s %-15s %-7s %-7s %-7s %-12s %-10s\n",
                    "ID", "Type", "Length", "Width", "Sqft", "Occupied", "Rate");
            while (rs.next()) {
                System.out.printf("%-5d %-15s %-7d %-7d %-7d %-12s %-10d\n",
                        rs.getInt("site_id"),
                        rs.getString("site_type"),
                        rs.getInt("length"),
                        rs.getInt("width"),
                        rs.getInt("sqft"),
                        rs.getBoolean("is_occupied"),
                        rs.getInt("maintenance_rate"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 2️⃣ View my payment status
    public void viewPaymentStatus(User user) {
        String sql = "SELECT p.total_amount, p.paid_amount, p.status, p.site_id " +
                     "FROM payment p " +
                     "JOIN owner o ON p.site_id = o.site_id " +
                     "WHERE o.user_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, user.getUserId());
            ResultSet rs = pst.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("No payment info found.");
                return;
            }

            System.out.println("\n--- MY PAYMENT STATUS ---");
            System.out.printf("%-5s %-12s %-12s %-10s\n", "ID", "Total Amt", "Paid Amt", "Status");
            while (rs.next()) {
                System.out.printf("%-5d %-12d %-12d %-10s\n",
                        rs.getInt("site_id"),
                        rs.getInt("total_amount"),
                        rs.getInt("paid_amount"),
                        rs.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 3️⃣ Request site update
    public void requestSiteUpdate(User user) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter description of update request: ");
        String desc = sc.nextLine();

        String getSite = "SELECT site_id FROM owner WHERE user_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst1 = con.prepareStatement(getSite)) {

            pst1.setInt(1, user.getUserId());
            ResultSet rs = pst1.executeQuery();

            if (!rs.next()) {
                System.out.println("No site found for you.");
                return;
            }

            int siteId = rs.getInt("site_id");

            String insert = "INSERT INTO update_request(owner_id, site_id, requested_change, status) VALUES(?, ?, ?, 'PENDING')";
            PreparedStatement pst2 = con.prepareStatement(insert);
            pst2.setInt(1, user.getUserId());
            pst2.setInt(2, siteId);
            pst2.setString(3, desc);
            pst2.executeUpdate();

            System.out.println("Update request submitted successfully. Waiting for Admin approval.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
