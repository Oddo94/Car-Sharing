package carsharing.controller.commands;

import carsharing.model.Car;
import carsharing.model.Company;
import carsharing.model.CompanyCar;
import carsharing.model.Customer;
import carsharing.model.enums.MenuType;
import carsharing.repository.CarSharingRepository;

import java.util.List;

public class Receiver {

    private CarSharingRepository repository;

    public Receiver(CarSharingRepository repository) {
        this.repository = repository;
    }

    public List<Company> getCompanyList() {
        List<Company> companiesList  = repository.getAllCompanies();

        return companiesList;
    }

    public int displayCompanyMenu(String companyName, boolean isFirstDisplay) {
        String companyMenu;
        if(isFirstDisplay) {
            companyMenu = String.format("\n'%s' company\n1. Car list\n2. Create a car\n0. Back", companyName);
        } else {
            companyMenu = String.format("\n1. Car list\n2. Create a car\n0. Back", companyName);
        }


        System.out.println(companyMenu);

        return 0;
    }

    public int displayMenu(MenuType menuType) {
        String menu;

        switch(menuType) {

            case GENERAL_MENU:
                menu = "1. Log in as a manager\n2. Log in as a customer\n3. Create a customer\n0. Exit";
                break;

            case MANAGER_MENU:
                menu = "1. Company list\n2. Create a company\n0. Back";
                break;

            case CUSTOMER_MENU:
                menu = "1. Rent a car\n2. Return a rented car\n3. My rented car\n0. Back";
                break;

            default:
                System.out.println("Invalid menu type!");
                return -1;
        }

        System.out.println(menu);

        return 0;

    }

    public int insertCompany(Company company) {
        int executionResult = repository.insertCompany(company);

        return executionResult;

    }

    public int displayCompanyCars(String companyName) {
        List<Car> carList = repository.getAllCompanyCars(companyName);

        int count = 1;
        if(carList.size() == 0) {
            System.out.println("\nThe car list is empty!");
            return -1;
        }

        System.out.println("\nCar list:");

        for(Car car : carList) {
            System.out.println(String.format("%d. %s", count++, car.getName()));
        }

        return 0;
    }

    public int insertCar(Car car, String companyName) {
        int executionResult = repository.insertCar(car, companyName);

        return executionResult;
    }

    public int getCarId(String carName) {
        int carId = repository.getCarId(carName);

        return carId;
    }

    public List<Customer> getCustomerList() {
        List<Customer> customerList = repository.getAllCustomers();

        return customerList;
    }

    public int insertCustomer(Customer customer) {
        int insertionResult = repository.insertCustomer(customer);

        return insertionResult;
    }

    public List<CompanyCar> getCompanyCars(String companyName) {
        List<CompanyCar> companyCarList = repository.getCarListForCompany(companyName);

        return companyCarList;
    }

    public int rentCar(Customer customer) {
        int rentalResult = repository.setRentedCarForCustomer(customer);

        return rentalResult;
    }

    public int returnRentedCar(Customer customer) {

        int executionResult = repository.returnRentedCar(customer);

        return executionResult;
    }

    public CompanyCar getCustomerRentedCar(String customerName) {
        CompanyCar companyCar = repository.getCustomerRentedCar(customerName);

        return companyCar;
    }

    public boolean hasRentedCar(String customerName) {

        return repository.hasRentedCar(customerName);
    }


}
