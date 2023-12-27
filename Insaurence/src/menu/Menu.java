
package menu;
import dao.InsuranceServiceImpl;
import entity.Policy;
import exception.PolicyNotFoundException;

import java.util.Scanner;

public class Menu {

    private static final InsuranceServiceImpl insuranceService = new InsuranceServiceImpl();
    private static final Scanner scanner = new Scanner(System.in);

    public static void menu() {
        int choice;

        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    createPolicy();
                    break;
                case 2:
                    getPolicy();
                    break;
                case 3:
                    getAllPolicies();
                    break;
                case 4:
                    updatePolicy();
                    break;
                case 5:
                    deletePolicy();
                    break;
                case 6:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }

        } while (choice != 6);
    }

    private static void displayMenu() {
        System.out.println("===== Insurance Management System Menu =====");
        System.out.println("1. Create Policy");
        System.out.println("2. Get Policy");
        System.out.println("3. Get All Policies");
        System.out.println("4. Update Policy");
        System.out.println("5. Delete Policy");
        System.out.println("6. Exit");
    }

    private static void createPolicy() {
       
        System.out.println("Creating a new policy...");

 
        System.out.print("Enter policy Id: ");
        int policyId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter policy name: ");
        String policyName = scanner.nextLine();
        System.out.print("Enter policy type: ");
        String policyType = scanner.nextLine();
        System.out.print("Enter coverage amount: ");
        double coverageAmount = scanner.nextDouble();

        Policy newPolicy = new Policy(policyId, policyName, policyType, coverageAmount);

        boolean success = insuranceService.createPolicy(newPolicy);

        if (success) {
            System.out.println("Policy created successfully.");
        } else {
            System.out.println("Failed to create policy. Please try again.");
        }
    }

    private static void getPolicy()  {

        System.out.println("Getting a policy...");


        System.out.print("Enter policy ID: ");
        int policyId = scanner.nextInt();


		Policy policy = insuranceService.getPolicy(policyId);
		if(policy!=null)
			System.out.println("Policy details:\n" + policy);
		
    }

    private static void getAllPolicies() {

        System.out.println("Getting all policies...");

        insuranceService.getAllPolicies().forEach(policy -> System.out.println(policy));
    }

    private static void updatePolicy() {

    	getAllPolicies();
        System.out.println("Updating a policy...");
        
        
        System.out.print("Enter policy ID to update: ");
        int policyId = scanner.nextInt();
        scanner.nextLine(); 
        if(insuranceService.getPolicy(policyId)!=null)
        {
        System.out.print("Enter new policy name: ");
        String newPolicyName = scanner.nextLine();
        System.out.print("Enter new policy type: ");
        String newPolicyType = scanner.nextLine();
        System.out.print("Enter new coverage amount: ");
        double newCoverageAmount = scanner.nextDouble();

        Policy updatedPolicy = new Policy(policyId, newPolicyName, newPolicyType, newCoverageAmount);


        boolean success = insuranceService.updatePolicy(updatedPolicy);

        if (success)
            System.out.println("Policy updated successfully.");
 
        }
        
    }

    private static void deletePolicy() {
 
    	
    	getAllPolicies();
    	
        System.out.println("\n \tDeleting a policy...");

        System.out.print("Enter policy ID to delete: ");
        int policyId = scanner.nextInt();


        boolean success = insuranceService.deletePolicy(policyId);

        if (success) {
            System.out.println("Policy deleted successfully.");
        } 
//        else {
//            System.out.println("Failed to delete policy. Please try again.");
//        }
    }
}
