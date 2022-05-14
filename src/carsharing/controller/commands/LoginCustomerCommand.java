package carsharing.controller.commands;

import carsharing.model.Customer;
import carsharing.utils.InputChecker;

import java.util.List;
import java.util.Scanner;

public class LoginCustomerCommand implements Command {
    private Receiver receiver;
    private Scanner scanner;
    private Invoker commandInvoker;


    public LoginCustomerCommand(Receiver receiver, Scanner scanner) {
        this.receiver = receiver;
        this.scanner = scanner;
        commandInvoker = new Invoker();
    }

    @Override
    public int execute() {
        List<Customer> customerList = receiver.getCustomerList();

        Command customerListCommand = new CustomerListCommand(receiver, scanner);
        commandInvoker.setCommand(customerListCommand);
        int displayResult = commandInvoker.executeCommand();
        //int displayResult = displayCustomerList(customerList);

        //Returns if the customer list is empty
        if (displayResult == -1) {
            return -1;
        }

        String input = scanner.nextLine().replace("> ", "").trim();

        if (!InputChecker.isDigit(input)) {
            return -1;
        }

        int inputNumber = Integer.parseInt(input);
        if (inputNumber != 0) {
            while (true) {
                int customerIndex = --inputNumber;

                String customerName = customerList.get(customerIndex).getName();

                //Invoker commandInvoker = new Invoker();
                Command customerMenuCommand = new CustomerMenuCommand(receiver, scanner, customerName);
                commandInvoker.setCommand(customerMenuCommand);

                //Executes the command that will display the rental menu
                int executionResult = commandInvoker.executeCommand();

                return executionResult;

            }

        } else {
            return -1;
        }
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
