package carsharing.controller.commands;

import carsharing.model.Car;

import java.util.Scanner;

public class InsertCarCommand implements Command {
    private Receiver receiver;
    private Scanner scanner;
    private String companyName;

    public InsertCarCommand(Receiver receiver, Scanner scanner, String companyName) {
        this.receiver = receiver;
        this.scanner = scanner;
        this.companyName = companyName;
    }

    @Override
    public int execute() {
        System.out.println("\nEnter the car name:");
        String carName = scanner.nextLine().replace("> ", "").trim();
        Car car = new Car(carName);

        int executionResult = receiver.insertCar(car, companyName);

        if(executionResult == 0) {
            System.out.println("The car was added!");
        }

        return executionResult;
    }
}
