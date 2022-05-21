package carsharing.controller.commands;

import carsharing.model.CompanyCar;

public class GetRentedCarCommand implements Command {
    private Receiver receiver;
    private String customerName;

    public GetRentedCarCommand(Receiver receiver, String customerName) {
        this.receiver = receiver;
        this.customerName = customerName;
    }

    @Override
    public int execute() {
        CompanyCar companyCar = receiver.getCustomerRentedCar(customerName);

        if(companyCar == null) {
            System.out.println("\nYou didn't rent a car!");
            return -1;
        }

        String companyCarInfo = String.format("\nYour rented car:\n%s\nCompany:\n%s", companyCar.getCarName(), companyCar.getCompanyName());
        System.out.println(companyCarInfo);

        return 0;
    }
}
