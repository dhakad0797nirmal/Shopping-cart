package shopping;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Authentication extends Customer {
    private Map<String, String> adminCredentials;
    private Map<String, Customer> customerMap = new HashMap<>();
    private Map<String, Product> p = new HashMap<>();
    private Map<String, Set<String>> customerProductMap = new HashMap<>();

    public Authentication() {
        adminCredentials = new HashMap<>();
        adminCredentials.put("nirmal", "123");
    }

    
    private static void addProductForCustomer(Map<String, Set<String>> map, String customerId, String productId) {
        map.computeIfAbsent(customerId, k -> new HashSet<>()).add(productId);
    }
    
    public void customerRegister(Customer cr) {
        customerMap.put(cr.getId(), cr);
        System.out.println("Customer Registered Successfully..");
    }

    public void adminLogin() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Admin Username: ");
        String adminUsername = scanner.nextLine();

        System.out.print("Enter Admin Password: ");
        String adminPassword = scanner.nextLine();

        if (authenticate(adminCredentials, adminUsername, adminPassword)) {
            System.out.println("Admin logged in successfully");

            while (true) {
                System.out.println("1. Add Product");
                System.out.println("2. Delete Product");
                System.out.println("3. List all Products");
                System.out.println("4. List all Customers");
                System.out.println("5. Exit");
                System.out.println("-------------------------");

                int choice;
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine(); 
                } catch (Exception e) {
                    System.out.println("Enter a valid number.");
                    scanner.nextLine(); 
                    continue; 
                }

                switch (choice) {
                    case 1:
                        // Logic for adding product
                        System.out.print("Enter Product Id: ");
                        String id = scanner.nextLine();
                        System.out.print("Enter Product Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Product Price: ");
                        float price = scanner.nextFloat();
                        Product po = new Product(id, name, price);
                        p.put(id, po);
                        break;
                    case 2:
                        // Logic for deleting product
                        System.out.println("Enter Product Id:");
                        String pid = scanner.nextLine();
                        if (p.containsKey(pid)) {
                            p.remove(pid);
                            System.out.println("Product removed successfully.");
                        } else {
                            System.out.println("Invalid product id!");
                        }
                        break;
                    case 3:
                        // Logic for listing all products
                        System.out.println("Available Products: ");
                        for (Map.Entry<String, Product> entry : p.entrySet()) {
                            String productid = entry.getKey();
                            Product product = entry.getValue();
                            System.out.println("Product ID: " + productid + ", Name: " + product.getName() + ", Price: " + product.getPrice());
                            System.out.println("----------------------------------------");
                        }
                        break;
                    case 4:
                        // Logic for listing all customers
                        System.out.println("Available Customers: ");
                        for (Map.Entry<String, Customer> entry : customerMap.entrySet()) {
                            String customertid = entry.getKey();
                            Customer customer = entry.getValue();
                            System.out.println("Customer ID: " + customertid + ", Name: " + customer.getCustomername() + ", Contact: " + customer.getCustomercontact());
                        }
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid input..");
                }
            }
        } else {
            System.out.println("Admin login failed..");
        }

       scanner.close(); 
    }

    public void customerLogin() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Customer Userid: ");
        String customerid = scanner.nextLine();

        System.out.print("Enter Customer Password: ");
        String customerpassword = scanner.nextLine();

        if (customerMap.containsKey(customerid)) {
            String password = customerMap.get(customerid).getPassword();
            System.out.println("Password for customer " + customerid + ": " + password);

            if (customerpassword.equals(password)) {
                System.out.println("Customer logged in Successfully !");

                while (true) {
                    System.out.println("Choose options:");
                    System.out.println("1. Products");
                    System.out.println("2. Cart");
                    System.out.println("3. CheckOut");
                    System.out.println("4. Exit");
                    System.out.println("-----------------");

                    int choice;
                    try {
                        choice = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline left after nextInt()
                    } catch (Exception e) {
                        System.out.println("Enter a valid number.");
                        scanner.nextLine(); // Consume the invalid input
                        continue; // Skip the rest of the loop and start from the beginning
                    }

                    switch (choice) {
                        case 1:
                        
                        	boolean continueatbuying = true;
                            while (continueatbuying) {
                            	
                            	
                            	System.out.println("Available products:");
                                for (Map.Entry<String, Product> entry : p.entrySet()) {
                                    String productid = entry.getKey();
                                    Product product = entry.getValue();
                                    System.out.println("Product ID: " + productid + ", Name: " + product.getName() + ", Price: " + product.getPrice());
                                }
                                System.out.println("=================================================");
                                System.out.println("Choose an option:");
                                System.out.println("1. Buy a product");
                                System.out.println("2. Back");

                                int option = scanner.nextInt();
                                scanner.nextLine(); // Consume the newline character

                                switch (option) {
                                    case 1:
                                    	boolean continueShopping = true;
                                    	
                                    	while(continueShopping){
                                        System.out.println("Available products:");
                                        for (Map.Entry<String, Product> entry : p.entrySet()) {
                                            String productid = entry.getKey();
                                            Product product = entry.getValue();
                                            System.out.println("Product ID: " + productid + ", Name: " + product.getName() + ", Price: " + product.getPrice());
                                            System.out.println("--------------------------------------------");
                                        }

                                        System.out.print("Enter product id: ");
                                        String productId = scanner.nextLine();

                                        addProductForCustomer(customerProductMap, customerid, productId);

                                        System.out.println("Do you want to continue shopping? (y/n)");
                                        String continueChoice = scanner.nextLine();
                                        if (!continueChoice.equalsIgnoreCase("y")) {
                                            continueShopping = false;
                                            System.out.println("Thank you for shopping!");
                                        } 
                                   }
                                        break;

                                    case 2:
                                        continueatbuying = false;
                                        break;

                                    default:
                                        System.out.println("Invalid option. Please choose again.");
                                }
                            }

                            
                         
                            break;
                        case 2:
                        	System.out.println("Selected Items : ");
                        	if (customerProductMap.containsKey(customerid)) {
                                Set<String> productIds = customerProductMap.get(customerid);
                                System.out.println("Product IDs for Customer ID - " + customerid + ": ");
                                for(String id:productIds) {
                                	Product po = p.get(id);
                                    System.out.println("Product ID: " + po.getId() + ", Name: " + po.getName() + " Price: " + po.getPrice());
                                }
                                System.out.println("------------------------------------------------");
                            } else {
                                System.out.println("No Product Purchased");
                            }
                            
                            break;
                        case 3:
                        	 System.out.printf("%-18s%n", "Bill....");
                        	 System.out.println("Product IDs for Customer ID - " + customerid + ": ");
                             System.out.println("====================================================");

                             if (customerProductMap.containsKey(customerid)) {
                                 Set<String> productIds = customerProductMap.get(customerid);
                                 double total = 0;

                                 System.out.printf("%-18s %-18s %-18s%n", "Product ID", "Name", "Price");
                                 for (String id : productIds) {
                                     Product po = p.get(id);
                                     System.out.printf("%-18s %-18s %-18s%n", po.getId(), po.getName(), po.getPrice());
                                     total += po.getPrice();
                                 }

                                 System.out.println("===================================================");
                                 System.out.printf("%-36s %-18s%n", "Total :", total);
                                 System.out.println("---------------------------------------------------");
                                 
                             } else {
                                 System.out.println("No Products Purchased ");
                             }
                        	
                            break;
                        case 4:
                            System.out.println("Exiting customer actions...");
                            return;    
                        default:
                            System.out.println("Invalid choice.");
                    }
                }
            } else {
                System.out.println("Password for customer " + customerid + " is Invalid");
            }
        } else {
            System.out.println("Customer not found.");
        }

        scanner.close();
    }

    private boolean authenticate(Map<String, String> credentials, String username, String password) {
        return credentials.containsKey(username) && credentials.get(username).equals(password);
    }
}
