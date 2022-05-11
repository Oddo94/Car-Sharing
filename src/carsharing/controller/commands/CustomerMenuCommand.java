package carsharing.controller.commands;

import carsharing.model.enums.MenuType;

public class CustomerMenuCommand implements Command {
    private Receiver receiver;
    //Will be used for executing the rental commands
    private String customerName;

    public CustomerMenuCommand(Receiver receiver, String customerName) {
        this.receiver = receiver;
        this.customerName = customerName;
    }


    @Override
    public int execute() {
        //Needs logic for handling the user selected option
        int executionResult = receiver.displayMenu(MenuType.CUSTOMER_MENU);

        //To add the logic for executing the rental commands

        return executionResult;
    }
}

