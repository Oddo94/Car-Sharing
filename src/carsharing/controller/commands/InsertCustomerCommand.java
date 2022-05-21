package carsharing.controller.commands;

import carsharing.model.Customer;

import java.util.Scanner;

public class InsertCustomerCommand implements Command {
    private Receiver receiver;
    private Scanner scanner;

    public InsertCustomerCommand(Receiver receiver, Scanner scanner) {
        this.receiver = receiver;
        this.scanner = scanner;
    }
    @Override
    public int execute() {
        System.out.println("\nEnter the customer name:");
        String input = scanner.nextLine().replaceAll("> ", "").trim();

        Customer customer = new Customer(input);
        int executionResult = receiver.insertCustomer(customer);

        if(executionResult == 0) {
            System.out.println("The customer was added!");
        }

        return executionResult;
    }
}
