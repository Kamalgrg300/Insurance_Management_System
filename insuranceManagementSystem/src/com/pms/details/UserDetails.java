package com.pms.details;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.pms.dao.AdminDao;
import com.pms.dao.CustomerDao;
import com.pms.model.Category;
import com.pms.model.Policy;

public class UserDetails {

    public static void adminPanel(Scanner scanner, AdminDao adminDao) {
        while (true) {
            System.out.println("\nAdmin Panel:");
            System.out.println("1. Add Category");
            System.out.println("2. Add Policy");
            System.out.println("3. View Categories");
            System.out.println("4. View Policies");
            System.out.println("5. Logout");
            System.out.print("Enter choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter category name: ");
                        String categoryName = scanner.nextLine();
                        adminDao.addCategory(categoryName);
                        System.out.println("Category added successfully.");
                        break;

                    case 2:
                        if (adminDao.getCategories().isEmpty()) {
                            System.out.println("No categories available. Please add a category first.");
                            break;
                        }

                        System.out.println("Select a category ID for the policy:");
                        for (Category c : adminDao.getCategories()) {
                            System.out.println(c.getId() + " - " + c.getName());
                        }

                        System.out.print("Enter category ID: ");
                        int categoryId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        System.out.print("Enter policy name: ");
                        String policyName = scanner.nextLine();
                        System.out.print("Enter policy description: ");
                        String policyDesc = scanner.nextLine();
                        adminDao.addPolicy(policyName, policyDesc, categoryId);
                        System.out.println("Policy added successfully.");
                        break;

                    case 3:
                        if (adminDao.getCategories().isEmpty()) {
                            System.out.println("No categories available.");
                        } else {
                            System.out.println("Categories:");
                            for (Category c : adminDao.getCategories()) {
                                System.out.println("- " + c.getId() + ": " + c.getName());
                            }
                        }
                        break;

                    case 4:
                        if (adminDao.getPolicies().isEmpty()) {
                            System.out.println("No policies available.");
                        } else {
                            System.out.println("Policies:");
                            for (Policy p : adminDao.getPolicies()) {
                                System.out.println("- " + p.getId() + ": " + p.getName() + " (" + p.getDescription() + ")");
                            }
                        }
                        break;

                    case 5:
                        System.out.println("Logging out from Admin Panel...");
                        return;

                    default:
                        System.out.println("Invalid choice, try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    public static void customerPanel(Scanner scanner, CustomerDao customerDao) {
        while (true) {
            System.out.println("\nCustomer Panel:");
            System.out.println("1. View Categories");
            System.out.println("2. View Policies");
            System.out.println("3. Apply for a Policy");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        if (customerDao.getCategories().isEmpty()) {
                            System.out.println("No categories available.");
                        } else {
                            System.out.println("Categories:");
                            for (Category c : customerDao.getCategories()) {
                                System.out.println("- " + c.getId() + ": " + c.getName());
                            }
                        }
                        break;

                    case 2:
                        if (customerDao.getPolicies().isEmpty()) {
                            System.out.println("No policies available.");
                        } else {
                            System.out.println("Policies:");
                            for (Policy p : customerDao.getPolicies()) {
                                System.out.println("- " + p.getId() + ": " + p.getName() + " (" + p.getDescription() + ")");
                            }
                        }
                        break;

                    case 3:
                        if (customerDao.getPolicies().isEmpty()) {
                            System.out.println("No policies available to apply.");
                            break;
                        }

                        System.out.println("Enter Policy ID to apply:");
                        int policyId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        if (customerDao.applyPolicy(policyId)) {
                            System.out.println("Policy applied successfully.");
                        } else {
                            System.out.println("Invalid Policy ID. Please try again.");
                        }
                        break;

                    case 4:
                        System.out.println("Logging out from Customer Panel...");
                        return;

                    default:
                        System.out.println("Invalid choice, try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
}
