package carsharing.controller;

import carsharing.controller.commands.*;
import carsharing.database.DatabaseManager;
import carsharing.model.enums.MenuType;
import carsharing.repository.CarSharingRepository;
import carsharing.utils.InputChecker;

import java.sql.Connection;
import java.util.Scanner;

public class UIManager {

    public void manageUI(String[] inputArguments) {
        Scanner scanner = new Scanner(System.in);

        Connection databaseConnection = DatabaseManager.getDatabaseConnection();
        CarSharingRepository repository = new CarSharingRepository(databaseConnection);

        Receiver commandReceiver = new Receiver(repository);
        Invoker commandInvoker = new Invoker();

        GENERAL_MENU: while(true) {
            commandReceiver.displayMenu(MenuType.GENERAL_MENU);
            String input = scanner.nextLine().replace("> ", "").trim();

            if (!InputChecker.isDigit(input)) {
                return;
            }
            int commandValue = Integer.parseInt(input);

            int executionResult;

            if (commandValue == 1) {
                MANAGER_MENU:
                while (true) {
                    Command loginManagerCommand = new LoginManagerCommand(commandReceiver);
                    commandInvoker.setCommand(loginManagerCommand);
                    executionResult = commandInvoker.executeCommand();

                    input = scanner.nextLine().replace("> ", "").trim();

                    if (!InputChecker.isDigit(input)) {
                        return;
                    }
                    commandValue = Integer.parseInt(input);
                    switch (commandValue) {
                        case 1:
                            Command displayCompaniesCommand = new CompanyListCommand(commandReceiver, scanner);
                            commandInvoker.setCommand(displayCompaniesCommand);
                            executionResult = commandInvoker.executeCommand();
                            break;


                        case 2:
                            Command insertCompanyCommand = new InsertCompanyCommand(commandReceiver, scanner);
                            commandInvoker.setCommand(insertCompanyCommand);
                            executionResult = commandInvoker.executeCommand();
                            break;

                        case 0:
                            break MANAGER_MENU;
                        default:
                            break;

                    }

                }
            } else if (commandValue == 2) {
                Command loginCustomerCommand = new LoginCustomerCommand(commandReceiver, scanner);
                commandInvoker.setCommand(loginCustomerCommand);
                commandInvoker.executeCommand();

            } else if(commandValue == 3) {
                Command insertCustomerCommand = new InsertCustomerCommand(commandReceiver,scanner);
                commandInvoker.setCommand(insertCustomerCommand);
                commandInvoker.executeCommand();

            } else {
                //Terminates the program
                break GENERAL_MENU;
            }
        }
    }

    private void displayMenu(MenuType menuType) {
        String menu;

        switch(menuType) {

            case GENERAL_MENU:
                menu = "1. Log in as a manager\n0. Exit";
                break;

            case MANAGER_MENU:
                menu = "1. Company list\n2. Create a company\n0. Back";
                break;

            default:
                System.out.println("Invalid menu type!");
                return;
        }

        System.out.println(menu);

    }
}
