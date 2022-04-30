package carsharing.controller.commands;

import carsharing.model.dto.CompanyDto;
import carsharing.model.enums.MenuType;
import carsharing.repository.CarSharingRepository;

import java.util.List;

public class Receiver {

    private CarSharingRepository repository;

    public Receiver(CarSharingRepository repository) {
        this.repository = repository;
    }

    public int displayCompanyList() {
        List<CompanyDto> companiesList  = repository.getAllCompanies();

        if(companiesList.size() > 0) {
            System.out.println("Company list:");
            companiesList.stream()
                    .forEach(companyDto -> System.out.println(String.format("%d. %s", companyDto.getId(), companyDto.getName())));

            System.out.println();

            //return 0;
        } else {
            System.out.println("The company list is empty!");
            System.out.println();
        }

        return 0;
    }

    public int insertCompany(CompanyDto companyDto) {
        int executionResult = repository.insertNewCompany(companyDto);

        return executionResult;

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

        System.out.println(menu);

        return 0;

    }

}
