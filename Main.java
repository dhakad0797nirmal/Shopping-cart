package shopping;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Authentication authenticate = new Authentication();

        Scanner sc = new Scanner(System.in);

        while (true) {
        	
            int choice;

            System.out.println("Choose option: ");
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Login");
            System.out.println("3. Customer Register");
            System.out.println("4. Exit");
            System.out.println("--------------------------");

            try {
                choice = sc.nextInt();
                sc.nextLine(); 
            } catch (InputMismatchException e) {
                System.out.println("Enter a valid number.");
                sc.nextLine(); 
                continue; 
            }

            switch (choice) {
                case 1:
                    authenticate.adminLogin();
                    break;
                case 2:
                    authenticate.customerLogin();
                    break;
                case 3:
                    System.out.print("Enter customer id: ");
                    String id = sc.nextLine();
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter contact: ");
                    String contact = sc.nextLine();
                    System.out.print("Enter address: ");
                    String address = sc.nextLine();

                    boolean passwordMatch = false;
                    String password = null;

                    while (!passwordMatch) {
                        System.out.print("Enter password: ");
                        password = sc.nextLine();
                        System.out.print("Confirm password: ");
                        String confirmPassword = sc.nextLine();
                        if (password.equals(confirmPassword)) {
                            passwordMatch = true;
                        } else {
                            System.out.println("Password does not match.");
                        }
                    }

                    Customer customer1 = new Customer(id, name, contact, address, password);
                    authenticate.customerRegister(customer1);
                    break;
                case 4:
                    System.out.println("Exiting....");
                    System.out.println(System.in.available());
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Enter valid option...!!!");
            }
        }
    }
}
