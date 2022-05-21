package carsharing.controller.commands;

import carsharing.model.enums.MenuType;
import carsharing.utils.InputChecker;

import java.util.Scanner;

public class CustomerMenuCommand implements Command {
    private Receiver receiver;
    //Will be used for executing the rental commands
    private Scanner scanner;
    private String customerName;
    private Invoker commandInvoker;

    public CustomerMenuCommand(Receiver receiver, Scanner scanner, String customerName) {
        this.receiver = receiver;
        this.scanner = scanner;
        this.customerName = customerName;
        this.commandInvoker = new Invoker();
    }


    @Override
    public int execute() {

        while(true) {

            int executionResult = receiver.displayMenu(MenuType.CUSTOMER_MENU);
            String input = scanner.nextLine().replaceAll(">\\s?", "").trim();

            if(!InputChecker.isDigit(input)) {
                return -1;
            }

            int commandNumber = Integer.parseInt(input);

            switch (commandNumber) {

                case 1:
                    Command rentCarCommand = new RentCarCommand(receiver, scanner, customerName);
                    commandInvoker.setCommand(rentCarCommand);
                    commandInvoker.executeCommand();
                    break;

                case 2:
                    Command returnCarCommand = new ReturnCarCommand(receiver, customerName);
                    commandInvoker.setCommand(returnCarCommand);
                    commandInvoker.executeCommand();
                    break;

                case 3:
                    Command getRentedCarCommand = new GetRentedCarCommand(receiver, customerName);
                    commandInvoker.setCommand(getRentedCarCommand);
                    commandInvoker.executeCommand();
                    break;
                //Back command
                case 0:
                    return -1;
            }
        }


        //To add the logic for executing the rental commands
    }
}

