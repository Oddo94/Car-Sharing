package carsharing.repository;

import carsharing.model.Car;
import carsharing.model.Company;
import carsharing.model.dao.CarDao;
import carsharing.model.dao.CompanyDao;
import carsharing.model.dto.CarDto;
import carsharing.model.dto.CompanyDto;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CarSharingRepository {

    private Connection databaseConnection;

    public CarSharingRepository(Connection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public List<CompanyDto> getAllCompanies() {
        CompanyDao companyDao = new CompanyDao(databaseConnection);

        List<Company> companiesList = companyDao.getAll();

        List<CompanyDto> resultList = companiesList.stream()
                .map(company -> new CompanyDto(company.getId(), company.getName()))
                .sorted(Comparator.comparing(companyDto -> companyDto.getId()))
                .collect(Collectors.toList());

        return resultList;
    }

    public int insertCompany(CompanyDto companyDto) {
        CompanyDao companyDao = new CompanyDao(databaseConnection);

        Company company = new Company(companyDto.getName());

        int insertionResult = companyDao.save(company);

        return insertionResult;
    }

    public List<CarDto> getAllCompanyCars(String companyName) {
        CarDao carDao = new CarDao(databaseConnection, companyName);

        List<Car> carList = carDao.getAll();

        if(carList.size() == 0) {
            return new ArrayList<CarDto>();
        }

        List<CarDto> resultList = carList
                .stream()
                .map(car -> new CarDto(car.getId(), car.getName()))
                .sorted(Comparator.comparing(carDto -> carDto.getId()))
                .collect(Collectors.toList());

        return resultList;

    }

    public int insertCar(Car car, String companyName) {
        CarDao carDao = new CarDao(databaseConnection, companyName);

        int insertionResult = carDao.save(car);

        return insertionResult;
    }
}
