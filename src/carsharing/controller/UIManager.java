package carsharing.controller;

import carsharing.controller.commands.*;
import carsharing.database.DatabaseManager;
import carsharing.model.dto.CompanyDto;
import carsharing.model.enums.MenuType;
import carsharing.repository.CarSharingRepository;

import java.sql.Connection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UIManager {

    public void manageUI(String[] inputArguments) {
        DatabaseManager.setupDatabase(inputArguments, false);
        Scanner scanner = new Scanner(System.in);

//        displayMenu(MenuType.GENERAL_MENU);
//        String input = scanner.nextLine().replace("> ", "").trim();

        GENERAL_MENU: while(true) {
            displayMenu(MenuType.GENERAL_MENU);
            String input = scanner.nextLine().replace("> ", "").trim();

            if(!isDigit(input)) {
                return;
            }
            int commandValue = Integer.parseInt(input);

            Connection databaseConnection = DatabaseManager.getDatabaseConnection();
            CarSharingRepository repository = new CarSharingRepository(databaseConnection);

            Receiver commandReceiver = new Receiver(repository);
            Invoker commandInvoker = new Invoker();

            int executionResult;

            if (commandValue == 1) {
                MANAGER_MENU: while (true) {
                    Command loginManagerCommand = new LoginManagerCommand(commandReceiver);
                    commandInvoker.setCommand(loginManagerCommand);
                    executionResult = commandInvoker.executeCommand();

//                    displayMenu(MenuType.MANAGER_MENU);
                    input = scanner.nextLine().replace("> ", "").trim();

                    if (!isDigit(input)) {
                        return;
                    }
                    commandValue = Integer.parseInt(input);
                    switch (commandValue) {
                        case 1:
                            Command displayCompaniesCommand = new CompanyListCommand(commandReceiver);
                            commandInvoker.setCommand(displayCompaniesCommand);
                            executionResult = commandInvoker.executeCommand();
                            //System.out.println("Inside display company list command...");
                            break;

                        case 2:
                            Command insertCompanyCommand = new InsertCompanyCommand(commandReceiver, scanner);
                            commandInvoker.setCommand(insertCompanyCommand);
                            executionResult = commandInvoker.executeCommand();

                            //System.out.println("Inside create company command...");
                            break;

                        case 0:
                            System.out.println("Inside back command...Returning to general menu");
                            //Goes back to general menu
                            break MANAGER_MENU;
                        default:
                            break;

                    }

                }
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

    private boolean isDigit(String input) {
        if(input == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("^\\d$");
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();

    }
}
