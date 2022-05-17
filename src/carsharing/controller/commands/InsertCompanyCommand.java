package carsharing.controller.commands;

import carsharing.model.Company;

import java.util.Scanner;

public class InsertCompanyCommand implements Command {
    private Receiver receiver;
    private Scanner scanner;

    public InsertCompanyCommand(Receiver receiver, Scanner scanner) {
        this.receiver = receiver;
        this.scanner = scanner;
    }


    @Override
    public int execute() {
        System.out.println("\nEnter the company name:");
        String companyName = scanner.nextLine().replace("> ", "").trim();
        Company company = new Company(companyName);

        int executionResult = receiver.insertCompany(company);

        if(executionResult == 0) {
            System.out.println("The company was created!");
        }


        return executionResult;
    }
}

