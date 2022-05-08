package carsharing.controller.commands;

import carsharing.model.dto.CarDto;
import carsharing.model.dto.CompanyDto;
import carsharing.model.enums.MenuType;
import carsharing.repository.CarSharingRepository;
import carsharing.utils.InputChecker;

import java.util.List;
import java.util.Scanner;



public class CompanyListCommand implements Command {
    private Receiver receiver;
    private Scanner scanner;
    private boolean isFirstDisplay;

    public CompanyListCommand(Receiver receiver, Scanner scanner) {
        this.receiver = receiver;
        this.scanner = scanner;
        this.isFirstDisplay = true;
    }

    @Override
    public int execute() {
        List<CompanyDto> companyList = receiver.getCompanyList();

        //Prints the list of retrieved companies
        if(companyList.size() > 0) {
            System.out.println("\nChoose the company:");
            companyList.stream()
                    .forEach(companyDto -> System.out.println(String.format("%d. %s", companyDto.getId(), companyDto.getName())));
        } else {
            System.out.println("The company list is empty!");
        }

        //Gets the id of the company as input
        String input = scanner.nextLine().replace("> ", "").trim();

        if(!InputChecker.isDigit(input)) {
            return -1;
        }

        int companyId = Integer.parseInt(input);

        //String companyName = companyList.get(companyId).getName();
        CompanyDto selectedCompany = companyList
                .stream()
                .filter(companyDto -> companyDto.getId() == companyId)
                .findAny()
                .orElse(null);

        String companyName = selectedCompany != null ? selectedCompany.getName() : "Unknown company";

        while(true) {
        Command companyMenuCommand = new CompanyMenuCommand(receiver, companyName, isFirstDisplay);
        //invoker used to execute all the commands inside the method
        Invoker commandInvoker = new Invoker();

        commandInvoker.setCommand(companyMenuCommand);
        commandInvoker.executeCommand();

        //Sets flag to false in order to prevent the display of the company name on subsequent loop executions
        isFirstDisplay = false;


        input = scanner.nextLine().replace("> ", "").trim();

        if(!InputChecker.isDigit(input)) {
            return -1;
        }

        int commandValue = Integer.parseInt(input);

            switch (commandValue) {
                case 1:
                    Command carListCommand = new CarListCommand(receiver, companyName);
                    commandInvoker.setCommand(carListCommand);
                    commandInvoker.executeCommand();
                    break;

                case 2:
                    Command insertCarCommand = new InsertCarCommand(receiver, scanner, companyName);
                    commandInvoker.setCommand(insertCarCommand);
                    commandInvoker.executeCommand();
                    break;

                case 0:
                    //receiver.displayMenu(MenuType.MANAGER_MENU);
                   return 0;

            }

        }
    }
}
