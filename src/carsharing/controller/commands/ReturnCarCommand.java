package carsharing.controller.commands;

import carsharing.model.Customer;

public class ReturnCarCommand implements Command {
    private Receiver receiver;
    private String customerName;

    public ReturnCarCommand(Receiver receiver, String customerName) {
        this.receiver = receiver;
        this.customerName = customerName;
    }

    @Override
    public int execute() {
        Customer customer = new Customer(0, customerName, 0);
       int executionResult = receiver.returnRentedCar(customer);

       if(executionResult == 0) {
           System.out.println("\nYou've returned a rented car!");
       }

       return executionResult;
    }
}
