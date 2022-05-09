package carsharing.controller.commands;

import carsharing.model.Car;
import carsharing.model.dto.CarDto;
import carsharing.model.dto.CompanyDto;
import carsharing.model.enums.MenuType;
import carsharing.repository.CarSharingRepository;

import java.util.List;

public class Receiver {

    private CarSharingRepository repository;

    public Receiver(CarSharingRepository repository) {
        this.repository = repository;
    }

    public List<CompanyDto> getCompanyList() {
        List<CompanyDto> companiesList  = repository.getAllCompanies();

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
                menu = "1. Log in as a manager\n0. Exit";
                break;

            case MANAGER_MENU:
                menu = "1. Company list\n2. Create a company\n0. Back";
                break;

            default:
                System.out.println("Invalid menu type!");
                return -1;
        }

        System.out.println();
        System.out.println(menu);

        return 0;

    }

    public int insertCompany(CompanyDto companyDto) {
        int executionResult = repository.insertCompany(companyDto);

        return executionResult;

    }

    public int displayCompanyCars(String companyName) {
        List<CarDto> carList = repository.getAllCompanyCars(companyName);

        int count = 1;
        if(carList.size() == 0) {
            System.out.println("\nThe car list is empty!");
            return -1;
        }

        System.out.println("\nCar list:");

        for(CarDto car : carList) {
            System.out.println(String.format("%d. %s", count++, car.getName()));
        }

        return 0;
    }

    public int insertCar(Car car, String companyName) {
        int executionResult = repository.insertCar(car, companyName);

        return executionResult;
    }

}
