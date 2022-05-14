package carsharing.controller.commands;

import carsharing.model.Customer;
import carsharing.utils.InputChecker;

import java.util.List;
import java.util.Scanner;

public class CustomerListCommand implements Command {
    private Receiver receiver;
    private Scanner scanner;
    private Invoker commandInvoker;

    public CustomerListCommand(Receiver receiver, Scanner scanner) {
        this.receiver = receiver;
        this.scanner = scanner;
        this.commandInvoker = new Invoker();

    }

    @Override
    public int execute() {
        List<Customer> customerList = receiver.getCustomerList();

        displayCustomerList(customerList);

        String input = scanner.nextLine().replaceAll(">\\s?", "").trim();

        if(!InputChecker.isDigit(input)) {
            return -1;
        }

        int customerNumber = Integer.parseInt(input);

        int customerIndex = --customerNumber;
        String customerName = customerList.get(customerIndex).getName();

        Command customerMenuCommand = new CustomerMenuCommand(receiver, scanner, customerName);
        commandInvoker.setCommand(customerMenuCommand);
        int executionResult = commandInvoker.executeCommand();

        return executionResult;
    }

    private int displayCustomerList(List<Customer> customerList) {
        int count = 1;
        if (customerList.size() == 0) {
            System.out.println("\nThe customer list is empty!");
            return -1;
        }

        System.out.println("\nChoose a customer:");
        for (Customer customer : customerList) {
            System.out.println(String.format("%d. %s", count++, customer.getName()));
        }
        System.out.println("0. Back");

        return 0;
    }
}
