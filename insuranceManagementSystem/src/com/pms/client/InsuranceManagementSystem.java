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

        boolean running = true , correctInput = true;
        

        while (running ) {
            System.out.println("\nInsurance Management System");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Forget Password");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
           
            Scanner scanner = new Scanner(System.in);
          
       
          
              
            
            	 int choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    System.out.print("Enter username: ");
                    String username = scanner.next();
                    System.out.print("Enter password: ");
                    String password = scanner.next();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Are you an Admin? (yes/no): ");
                    String role = scanner.nextLine();
                    
                    if (role.equalsIgnoreCase("yes")) {
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
                    String username = scanner.next();
                    System.out.print("Enter password: ");
                    String password = scanner.next();
                    User user = userDao.login(username, password);
                    if (user != null) {
                        System.out.println("Welcome, " + user.getUsername());
                        UserDetails u = new UserDetails();
                        if (user instanceof
                        		Admin) {
                        	
                            u.adminPanel(scanner, adminDao);
                        } else {
                            u.customerPanel(scanner, customerDao);
                        }
                    } else {
                        System.out.println("Invalid username or password.");
                    }
                    break;
                }
                case 3: {
                    System.out.print("Enter email: ");
                    String email = scanner.next();
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

	
    
    
   
