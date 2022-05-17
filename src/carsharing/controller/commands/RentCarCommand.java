package carsharing.controller.commands;

import carsharing.model.Company;
import carsharing.model.CompanyCar;
import carsharing.model.Customer;
import carsharing.model.Model;
import carsharing.utils.InputChecker;

import java.util.List;
import java.util.Scanner;

public class RentCarCommand implements Command {
    private Receiver receiver;
    private Scanner scanner;
    private String customerName;
    private Invoker commandInvoker;

    public RentCarCommand(Receiver receiver, Scanner scanner, String customerName) {
        this.receiver = receiver;
        this.scanner = scanner;
        this.customerName = customerName;
        commandInvoker = new Invoker();
    }
//    @Override
//    public int execute() {
//        //Company list display
//        List<Company> companyList = receiver.getCompanyList();
//        int companyDisplayResult = displayElements(companyList, "company");
//
//        if(companyDisplayResult == -1) {
//            return -1;
//        }
//
//        String input = scanner.nextLine().replaceAll(">\\s?", "").trim();
//
//        if(!InputChecker.isDigit(input)) {
//            return -1;
//        }
//
//        int commandValue = Integer.parseInt(input);
//        //Back command
//        if(commandValue == 0) {
//            return -1;
//        }
//
//        //int companyIndex = --commandValue;
//
//        //String companyName = companyList.get(companyIndex).getName();
//        String companyName = getElementName(companyList, commandValue);
//
//
//        //Company car list display
//        List<CompanyCar> companyCarList = receiver.getCompanyCars(companyName);
//        int companyCarDisplayResult = displayElements(companyCarList, "car");
//
//        input = scanner.nextLine().replaceAll(">\\s?", "").trim();
//
//        if(!InputChecker.isDigit(input)) {
//            return -1;
//        }
//
//        commandValue = Integer.parseInt(input);
//        //Back command
//        if(commandValue == 0) {
//            return -1;
//        }
//
//        String carName = getElementName(companyCarList, commandValue);
//
//        int carId = receiver.getCarId(carName);
//
//         Customer customer = new Customer(0, customerName, carId);
//
//         int rentCarExecutionResult = receiver.rentCar(customer);
//
//         if(rentCarExecutionResult == 0) {
//             System.out.println(String.format("\nYou rented '%s'", carName));
//             return 0;
//         } else if(rentCarExecutionResult == -1) {
//             System.out.println("You've already rented a car!");
//             return -1;
//         }
//
//        return 0;
//    }

    @Override
    public int execute() {
        //Returns if the user has already rented a car
        if(receiver.hasRentedCar(customerName)) {
            System.out.println("\nYou've already rented a car!");
            return -1;
        }

        //Company list display
        List<Company> companyList = receiver.getCompanyList();

        int companyDisplayResult = displayElements(companyList, "company");

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

        //int companyIndex = --commandValue;

        //String companyName = companyList.get(companyIndex).getName();
        String companyName = getElementName(companyList, commandValue);


        //Company car list display
        List<CompanyCar> companyCarList = receiver.getCompanyCars(companyName);
        int companyCarDisplayResult = displayElements(companyCarList, "car");

        input = scanner.nextLine().replaceAll(">\\s?", "").trim();

        if(!InputChecker.isDigit(input)) {
            return -1;
        }

        commandValue = Integer.parseInt(input);
        //Back command
        if(commandValue == 0) {
            return -1;
        }

        String carName = getElementName(companyCarList, commandValue);

        int carId = receiver.getCarId(carName);

        Customer customer = new Customer(0, customerName, carId);

        int rentCarExecutionResult = receiver.rentCar(customer);

        if(rentCarExecutionResult == 0) {
            System.out.println(String.format("\nYou rented '%s'", carName));
            return 0;
        }
//        } else if(rentCarExecutionResult == -1) {
//            System.out.println("You've already rented a car!");
//            return -1;
//        }

        return 0;
    }

    private String getElementName(List<? extends Model> inputList, int itemPosition) {
        if(inputList.size() == 0) {
            return null;
        }
        int itemIndex = --itemPosition;

        if(itemIndex < 0 || itemIndex > inputList.size() - 1) {
            return null;
        }

        String itemName = inputList.get(itemIndex).getName();

        return itemName;

    }

    private int displayElements(List<? extends Model> itemList, String itemName) {
        if(itemList.size() == 0) {
            System.out.println(String.format("The %s list is empty!", itemName));
            return -1;
        }

        int count = 1;
        System.out.println(String.format("\nChoose a %s:", itemName));
        for(Model model : itemList) {
            System.out.println(String.format("%d. %s", count++, model.getName()));
        }
        System.out.println("0. Back");

        return 0;
    }
}
