package carsharing.controller.commands;

import carsharing.model.Customer;

import java.util.List;

public class CustomerListCommand implements Command {
    private Receiver receiver;

    public CustomerListCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public int execute() {
        List<Customer> customerList = receiver.getCustomerList();

        int executionResult = displayCustomerList(customerList);

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
