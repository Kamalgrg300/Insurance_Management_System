package com.pms.details;

import com.pms.dao.AdminDao;
import com.pms.model.Category;
import com.pms.model.SubCategory;
import com.pms.model.Policy;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserDetails {

    // Customer Panel: Displays main categories; when a customer selects one, its policies are shown.
    public static void customerPanel(Scanner scanner, AdminDao adminDao) {
        boolean customerRunning = true;
        while (customerRunning) {
            System.out.println("\nCustomer Panel:");
            System.out.println("1. View Insurance Categories");
            System.out.println("2. Logout");
            System.out.print("Enter your choice: ");
            
            int choice = 0;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); // Clear invalid input
                continue;
            }
            
            switch (choice) {
                case 1:
                    // Display main insurance categories
                    List<Category> categories = adminDao.getCategories();
                    if (categories.isEmpty()) {
                        System.out.println("No categories available.");
                    } else {
                        System.out.println("Insurance Categories:");
                        for (Category category : categories) {
                            System.out.println(category.getId() + ". " + category.getName());
                        }
                        System.out.print("Enter the Category ID to view its policies (or 0 to go back): ");
                        int catId = 0;
                        try {
                            catId = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a valid number.");
                            scanner.nextLine();
                            break;
                        }
                        if (catId == 0) {
                            break; // go back to the customer panel menu
                        }
                        // Find the selected category
                        Category selectedCategory = null;
                        for (Category category : categories) {
                            if (category.getId() == catId) {
                                selectedCategory = category;
                                break;
                            }
                        }
                        if (selectedCategory == null) {
                            System.out.println("Invalid Category ID.");
                            break;
                        }
                        // Display subcategories (policies) of the selected category
                        List<SubCategory> subCategories = selectedCategory.getSubCategories();
                        if (subCategories.isEmpty()) {
                            System.out.println("No policies available in this category.");
                        } else {
                            System.out.println("Available Policies under " + selectedCategory.getName() + ":");
                            for (SubCategory sub : subCategories) {
                                System.out.println("- " + sub.getSubCategoryId() + ": " + sub.getSubCategoryName());
                            }
                            System.out.print("Enter the Policy ID to apply for (or 0 to go back): ");
                            int policyId = 0;
                            try {
                                policyId = scanner.nextInt();
                                scanner.nextLine();
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input! Please enter a valid number.");
                                scanner.nextLine();
                                break;
                            }
                            if (policyId == 0) {
                                break;
                            }
                            boolean found = false;
                            for (SubCategory sub : subCategories) {
                                if (sub.getSubCategoryId() == policyId) {
                                    found = true;
                                    System.out.println("Policy " + sub.getSubCategoryName() + " applied successfully.");
                                    break;
                                }
                            }
                            if (!found) {
                                System.out.println("Invalid Policy ID. Please try again.");
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println("Logging out from Customer Panel...");
                    customerRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Admin Panel: Allows viewing, adding, and editing categories, subcategories, and policies.
    public static void adminPanel(Scanner scanner, AdminDao adminDao) {
        boolean adminRunning = true;
        while (adminRunning) {
            System.out.println("\nAdmin Panel:");
            System.out.println("1. View Categories (with SubCategories)");
            System.out.println("2. Add Category");
            System.out.println("3. Edit Category");
            System.out.println("4. Add Policy (SubCategory) to a Category");
            System.out.println("5. Edit Policy (SubCategory)"); // This edits the SubCategory record
            System.out.println("6. View Policies");
            System.out.println("7. Edit Policy"); // This edits the Policy record (name & description)
            System.out.println("8. Logout");
            System.out.print("Enter your choice: ");
            
            int choice = 0;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
                continue;
            }
            
            switch (choice) {
                case 1:
                    // View categories and their subcategories
                    List<Category> categories = adminDao.getCategories();
                    if (categories.isEmpty()) {
                        System.out.println("No categories found.");
                    } else {
                        System.out.println("Categories:");
                        for (Category cat : categories) {
                            System.out.println(cat.getId() + ". " + cat.getName());
                            List<SubCategory> subs = cat.getSubCategories();
                            if (!subs.isEmpty()) {
                                System.out.println("   SubCategories:");
                                for (SubCategory sub : subs) {
                                    System.out.println("     " + sub.getSubCategoryId() + ". " + sub.getSubCategoryName());
                                }
                            }
                        }
                    }
                    break;
                case 2:
                    // Add category
                    System.out.print("Enter the name of the new category: ");
                    String newCategory = scanner.nextLine();
                    adminDao.addCategory(newCategory);
                    System.out.println("Category added successfully.");
                    break;
                case 3:
                    // Edit category
                    System.out.print("Enter the Category ID to edit: ");
                    int catIdToEdit = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter the new name for the category: ");
                    String newCatName = scanner.nextLine();
                    adminDao.updateCategory(catIdToEdit, newCatName);
                    break;
                case 4:
                    // Add subcategory (policy)
                    System.out.print("Enter the category ID where you want to add a policy: ");
                    int categoryId = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter the name of the new policy: ");
                    String policyName = scanner.nextLine();
                    System.out.print("Enter a description for the policy: ");
                    String policyDescription = scanner.nextLine();
                    adminDao.addSubCategory(categoryId, policyName, policyDescription);
                    System.out.println("Policy added successfully.");
                    break;
                case 5:
                    // Edit subcategory (policy) name (the SubCategory record)
                    System.out.print("Enter the Policy (SubCategory) ID to edit: ");
                    int subCatIdToEdit = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter the new name for the policy (SubCategory): ");
                    String newPolicySubName = scanner.nextLine();
                    adminDao.updateSubCategory(subCatIdToEdit, newPolicySubName);
                    break;
                case 6:
                    // View all policies from the policies list
                    List<Policy> policyList = adminDao.getPolicies();
                    if (policyList.isEmpty()) {
                        System.out.println("No policies available.");
                    } else {
                        System.out.println("Policies:");
                        for (Policy pol : policyList) {
                            System.out.println(pol.getId() + ". " + pol.getName() + " (Description: " + pol.getDescription() + ", Category ID: " + pol.getCategoryId() + ")");
                        }
                    }
                    break;
                case 7:
                    // Edit policy details (the Policy record)
                    System.out.print("Enter the Policy ID to edit: ");
                    int policyIdToEdit = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter the new name for the policy: ");
                    String newPolicyName = scanner.nextLine();
                    System.out.print("Enter the new description for the policy: ");
                    String newPolicyDescription = scanner.nextLine();
                    adminDao.updatePolicy(policyIdToEdit, newPolicyName, newPolicyDescription);
                    break;
                case 8:
                    System.out.println("Logging out from Admin Panel...");
                    adminRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }
}
