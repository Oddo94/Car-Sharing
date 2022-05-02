package carsharing.controller.commands;

import carsharing.model.dto.CompanyDto;

import java.util.Scanner;

public class InsertCompanyCommand implements Command {
    private Receiver receiver;
    //private CompanyDto companyDto;
    private Scanner scanner;

    public InsertCompanyCommand(Receiver receiver, Scanner scanner) {
        this.receiver = receiver;
        //this.companyDto = companyDto;
        this.scanner = scanner;
    }


    @Override
    public int execute() {
        System.out.println("\nEnter the company name:");
        String companyName = scanner.nextLine().replace("> ", "").trim();
        CompanyDto companyDto = new CompanyDto(companyName);

        int executionResult = receiver.insertCompany(companyDto);

        if(executionResult == 0) {
            System.out.println("The company was created!");
        }


        return executionResult;
    }
}

