package carsharing.controller.commands;

import carsharing.model.dto.CompanyDto;
import carsharing.model.enums.MenuType;
import carsharing.utils.InputChecker;

import java.util.List;
import java.util.Scanner;

public class RentCarCommand implements Command {
    private Receiver receiver;
    private Scanner scanner;
    private Invoker commandInvoker;

    public RentCarCommand(Receiver receiver, Scanner scanner) {
        this.receiver = receiver;
        this.scanner = scanner;
        commandInvoker = new Invoker();
    }
    @Override
    public int execute() {
//        Command companyListCommand = new CompanyListCommand(receiver, scanner);
//        commandInvoker.setCommand(companyListCommand);
//        commandInvoker.executeCommand();

        List<CompanyDto> companyList = receiver.getCompanyList();
        int companyDisplayResult = displayCompanies(companyList);

        if(companyDisplayResult == -1) {
            return -1;
        }

        String input = scanner.nextLine().replaceAll(">\\s?", "").trim();

        if(!InputChecker.isDigit(input)) {
            return -1;
        }

        int commandValue = Integer.parseInt(input);
        //Back command
        if(commandValue == 0) {
            return -1;
        }

        int companyIndex = --commandValue;

        String companyName = companyList.get(companyIndex).getName();

        Command companyCarListCommand = new CompanyCarListCommand(receiver, scanner, companyName);
        commandInvoker.setCommand(companyCarListCommand);
        int companyCarDisplayResult = commandInvoker.executeCommand();







        return 0;
    }

    private int displayCompanies(List<CompanyDto> companyList) {
        if(companyList.size() == 0) {
            System.out.println("The company list is empty!");
            return -1;
        }

        int count = 1;
        System.out.println("Choose a company:\n");
        for(CompanyDto company : companyList) {
            System.out.println(String.format("%d. %s", count++, company.getName()));
        }
        System.out.println("0. Back");

        return 0;
    }
}
