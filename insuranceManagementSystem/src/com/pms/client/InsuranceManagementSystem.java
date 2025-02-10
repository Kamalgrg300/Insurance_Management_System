package com.pms.client;

import com.pms.dao.*;
import com.pms.dao.impl.*;
import com.pms.model.*;
import com.pms.details.*;
import java.util.*;

public class InsuranceManagementSystem {
    public static void main(String[] args) {
       
        UserDao userDao = new UserDaoImpl();
        AdminDao adminDao = new AdminDaoImpl();
        CustomerDao customerDao = new CustomerDaoImpl(adminDao.getCategories(), adminDao.getPolicies());

        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        while (running) {
            System.out.println("\nInsurance Management System");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Forget Password");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number between 1-4.");
                scanner.next();
                continue;
            }
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: {
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();

                    String role;
                    while (true) {
                        System.out.print("Are you an Admin? (yes/no): ");
                        role = scanner.nextLine().trim().toLowerCase();
                        if (role.equals("yes") || role.equals("no")) {
                            break;
                        } else {
                            System.out.println("Invalid input! Please enter 'yes' or 'no'.");
                        }
                    }

                    if (role.equals("yes")) {
                        userDao.register(new Admin(username, password, email));
                        System.out.println("Admin registered successfully.");
                    } else {
                        userDao.register(new Customer(username, password, email));
                        System.out.println("Customer registered successfully.");
                    }
                    break;
                }
                case 2: {
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    User user = userDao.login(username, password);
                    if (user != null) {
                        System.out.println("Welcome, " + user.getUsername());
                        if (user instanceof Admin) {
                            UserDetails.adminPanel(scanner, adminDao);  // Corrected call
                        } else {
                            UserDetails.customerPanel(scanner, customerDao);  // Corrected call
                        }
                    } else {
                        System.out.println("Invalid username or password.");
                    }
                    break;
                }
                case 3: {
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    userDao.forgetPassword(email);
                    break;
                }
                case 4: {
                    scanner.close();
                    System.out.println("Exiting system. Thank you!");
                    running = false;
                    break;
                }
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
