package admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import util.DBConnection;

public class AdminService {

    // 1️⃣ View all sites
    public void viewAllSites() {
        String sql = "SELECT site_id, site_type, length, width, sqft, is_occupied, maintenance_rate FROM site ORDER BY site_id";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            System.out.println("\n--- ALL SITES ---");
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

    // 2️⃣ View pending payments
    public void viewPendingPayments() {
        String sql = "SELECT p.site_id, o.name AS owner_name, p.total_amount, p.paid_amount, p.status " +
                     "FROM payment p " +
                     "JOIN owner o ON p.site_id = o.site_id " +
                     "WHERE p.status='PENDING' ORDER BY p.site_id";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            System.out.println("\n--- PENDING PAYMENTS ---");
            System.out.printf("%-5s %-15s %-12s %-12s %-10s\n",
                    "ID", "Owner", "Total Amt", "Paid Amt", "Status");
            while (rs.next()) {
                System.out.printf("%-5d %-15s %-12d %-12d %-10s\n",
                        rs.getInt("site_id"),
                        rs.getString("owner_name"),
                        rs.getInt("total_amount"),
                        rs.getInt("paid_amount"),
                        rs.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 3️⃣ Approve/Reject update requests
    public void approveRejectRequests() {
        try (Connection con = DBConnection.getConnection()) {
            String fetch = "SELECT request_id, owner_id, site_id, requested_change, status FROM update_request WHERE status='PENDING'";
            PreparedStatement pst = con.prepareStatement(fetch);
            ResultSet rs = pst.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("\nNo pending update requests.");
                return;
            }

            Scanner sc = new Scanner(System.in);
            while (rs.next()) {
                int requestId = rs.getInt("request_id");
                int ownerId = rs.getInt("owner_id");
                int siteId = rs.getInt("site_id");
                String change = rs.getString("requested_change");
                System.out.println("\nRequest ID: " + requestId + ", Owner ID: " + ownerId + ", Site ID: " + siteId);
                System.out.println("Requested Change: " + change);
                System.out.print("Approve (A) / Reject (R) / Skip (S): ");
                String choice = sc.nextLine().toUpperCase();

                if (choice.equals("A")) {
                    String update = "UPDATE update_request SET status='APPROVED' WHERE request_id=?";
                    PreparedStatement pst2 = con.prepareStatement(update);
                    pst2.setInt(1, requestId);
                    pst2.executeUpdate();
                    System.out.println("Request Approved.");
                } else if (choice.equals("R")) {
                    String update = "UPDATE update_request SET status='REJECTED' WHERE request_id=?";
                    PreparedStatement pst2 = con.prepareStatement(update);
                    pst2.setInt(1, requestId);
                    pst2.executeUpdate();
                    System.out.println("Request Rejected.");
                } else {
                    System.out.println("Skipped.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 4️⃣ Collect maintenance
    public void collectMaintenance() {
        try (Connection con = DBConnection.getConnection()) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Site ID to collect payment: ");
            int siteId = sc.nextInt();
            sc.nextLine();

            String check = "SELECT total_amount, paid_amount, status FROM payment WHERE site_id=?";
            PreparedStatement pst = con.prepareStatement(check);
            pst.setInt(1, siteId);
            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                System.out.println("Site not found!");
                return;
            }

            int total = rs.getInt("total_amount");
            int paid = rs.getInt("paid_amount");
            String status = rs.getString("status");

            System.out.println("Total: " + total + ", Paid: " + paid + ", Status: " + status);
            if (status.equals("PAID")) {
                System.out.println("Payment already completed.");
                return;
            }

            System.out.print("Enter amount to pay: ");
            int amt = sc.nextInt();
            sc.nextLine();

            int newPaid = paid + amt;
            String newStatus = (newPaid >= total) ? "PAID" : "PENDING";

            String update = "UPDATE payment SET paid_amount=?, status=? WHERE site_id=?";
            PreparedStatement pst2 = con.prepareStatement(update);
            pst2.setInt(1, newPaid);
            pst2.setString(2, newStatus);
            pst2.setInt(3, siteId);
            pst2.executeUpdate();

            System.out.println("Payment updated successfully. New Paid Amount: " + newPaid + ", Status: " + newStatus);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
