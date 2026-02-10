import dao.UserDAO;
import model.User;
import owner.OwnerService;

import java.util.Scanner;

import admin.AdminService;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        System.out.println("===== Layout Maintenance App =====");
        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        User user = userDAO.login(username, password);

        if (user == null) {
            System.out.println("Invalid username or password!");
            return;
        }

        System.out.println("Login successful! Role: " + user.getRole());

        if (user.getRole().equalsIgnoreCase("ADMIN")) {
            adminMenu();
        } else {
            ownerMenu(user);
        }

        sc.close();
    }

    public static void adminMenu() {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("\n=== ADMIN MENU ===");
            System.out.println("1. View all sites");
            System.out.println("2. View pending payments");
            System.out.println("3. Approve/Reject update requests");
            System.out.println("4. Collect maintenance");
            System.out.println("5. Logout");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline
            
            AdminService adminService = new AdminService();

            switch (choice) {
                case 1:
                    System.out.println("Viewing all sites...");
                    adminService.viewAllSites();
                    break;
                case 2:
                    System.out.println("Viewing pending payments...");
                    adminService.viewPendingPayments();
                    break;
                case 3:
                    System.out.println("Approve/Reject update requests...");
                    adminService.approveRejectRequests();
                    break;
                case 4:
                    System.out.println("Collecting maintenance...");
                    adminService.collectMaintenance();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice, try again!");
            }
        } while (choice != 5);
    }

    public static void ownerMenu(User user) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("\n=== OWNER MENU ===");
            System.out.println("Welcome, " + user.getUsername());
            System.out.println("1. View my site");
            System.out.println("2. View my payment status");
            System.out.println("3. Request site update");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            OwnerService ownerService = new OwnerService();

            switch (choice) {
                case 1:
                    System.out.println("Viewing your site...");
                    ownerService.viewMySite(user);
                    break;
                case 2:
                    System.out.println("Viewing your payment status...");
                    ownerService.viewPaymentStatus(user);
                    break;
                case 3:
                    System.out.println("Requesting site update...");
                    ownerService.requestSiteUpdate(user);
                    break;
                case 4:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice, try again!");
            }
        } while (choice != 4);
    }

}
