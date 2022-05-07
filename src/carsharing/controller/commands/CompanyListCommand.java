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

    public CompanyListCommand(Receiver receiver, Scanner scanner) {
        this.receiver = receiver;
        this.scanner = scanner;
    }

    @Override
    public int execute() {
        List<CompanyDto> companyList = receiver.getCompanyList();

        //Prints the list of retrieved companies
        if(companyList.size() > 0) {
            System.out.println("\nChoose a company:");
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

        String companyName = companyList.get(companyId).getName();

        while(true) {
        Command companyMenuCommand = new CompanyMenuCommand(receiver, companyName);
        //invoker used to execute all the command inside the method
        Invoker commandInvoker = new Invoker();

        commandInvoker.setCommand(companyMenuCommand);
        commandInvoker.executeCommand();


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

                case 2:
                    Command insertCarCommand = new InsertCarCommand(receiver, scanner, companyName);
                    commandInvoker.setCommand(insertCarCommand);
                    commandInvoker.executeCommand();

                case 3:
                    receiver.displayMenu(MenuType.GENERAL_MENU);
                    return 0;
            }

        }
    }
}
