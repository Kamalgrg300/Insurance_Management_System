package com.pms.details;

import java.util.Scanner;

import com.pms.dao.AdminDao;
import com.pms.dao.CustomerDao;
import com.pms.model.Category;
import com.pms.model.Policy;

public class UserDetails {
	
	public static void adminPanel(Scanner scanner, AdminDao adminDao) {
        boolean adminRunning = true;
        while (adminRunning) {
            System.out.println("\nAdmin Panel:");
            System.out.println("1. Add Category");
            System.out.println("2. Add Policy");
            System.out.println("3. View Categories");
            System.out.println("4. View Policies");
            System.out.println("5. Logout");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter category name: ");
                    String categoryName = scanner.nextLine();
                    adminDao.addCategory(categoryName);
                    System.out.println("Category added successfully.");
                    break;
                case 2:
                    System.out.print("Enter policy name: ");
                    String policyName = scanner.nextLine();
                    System.out.print("Enter policy description: ");
                    String policyDesc = scanner.nextLine();
                    adminDao.addPolicy(policyName, policyDesc, 1);
                    System.out.println("Policy added successfully.");
                    break;
                case 3:
                    System.out.println("Categories:");
                    for (Category c : adminDao.getCategories()) {
                        System.out.println("- " + c.getName());
                    }
                    break;
                case 4:
                    System.out.println("Policies:");
                    for (Policy p : adminDao.getPolicies()) {
                        System.out.println("- " + p.getName() + " (" + p.getDescription() + ")");
                    }
                    break;
                case 5:
                    adminRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
	public static void customerPanel(Scanner scanner, CustomerDao customerDao) {
        boolean customerRunning = true;
        while (customerRunning) {
            System.out.println("\nCustomer Panel:");
            System.out.println("1. View Categories");
            System.out.println("2. View Policies");
            System.out.println("3. Apply for a Policy");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Categories:");
                    for (Category c : customerDao.getCategories()) {
                        System.out.println("- " + c.getName());
                    }
                    break;
                case 2:
                    System.out.println("Policies:");
                    for (Policy p : customerDao.getPolicies()) {
                        System.out.println("- " + p.getName() + " (" + p.getDescription() + ")");
                    }
                    break;
                case 3:
                    System.out.print("Enter Policy ID to apply: ");
                    int policyId = scanner.nextInt();
                    customerDao.applyPolicy(policyId);
                    break;
                case 4:
                    customerRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
    

}
