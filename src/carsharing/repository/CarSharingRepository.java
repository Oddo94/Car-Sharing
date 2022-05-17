package carsharing.repository;

import carsharing.model.Car;
import carsharing.model.Company;
import carsharing.model.CompanyCar;
import carsharing.model.Customer;
import carsharing.model.dao.*;
import carsharing.model.dto.CarDto;
import carsharing.model.dto.CompanyDto;
import carsharing.model.dto.CustomerDto;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CarSharingRepository {

    private Connection databaseConnection;
    private Dao dao;

    public CarSharingRepository(Connection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public List<Company> getAllCompanies() {
        CompanyDao companyDao = new CompanyDao(databaseConnection);

        List<Company> companyList = companyDao.getAll();

//        List<CompanyDto> resultList = companiesList.stream()
//                .map(company -> new CompanyDto(company.getId(), company.getName()))
//                .sorted(Comparator.comparing(companyDto -> companyDto.getId()))
//                .collect(Collectors.toList());

       // return resultList;
        return companyList;
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

    public int setRentedCarForCustomer(Customer customer) {
        Dao customerDao = new CustomerDao(databaseConnection);

       int carRentalResult =  customerDao.update(0L, customer);

       return carRentalResult;
    }

    public int getCarId(String carName) {
        Dao carDao = new CarDao(databaseConnection);

        int carId = carDao.getItemId(carName);

        return carId;
    }

    public List<Customer> getAllCustomers() {
        CustomerDao customerDao = new CustomerDao(databaseConnection);

        List<Customer> customerList = customerDao.getAll();

//        List<CustomerDto> resultList = customerList
//                .stream()
//                .sorted(Comparator.comparing(customer -> customer.getId()))
//                .map(customer -> new CustomerDto(customer.getName()))
//                .collect(Collectors.toList());

        return customerList;
    }

    public int insertCustomer(Customer customer) {
        CustomerDao customerDao = new CustomerDao(databaseConnection);

        int insertionResult = customerDao.save(customer);

        return insertionResult;
    }

    public List<CompanyCar> getCarListForCompany(String companyName) {
        dao = new CompanyCarDao(databaseConnection, companyName);
        List<CompanyCar> companyCarList = dao.getAll();

        return companyCarList;
    }

    public int returnRentedCar(Customer customer) {
        dao = new CustomerDao(databaseConnection);
        int executionResult = dao.update(0l, customer);

        return executionResult;
    }

    public CompanyCar getCustomerRentedCar(String customerName) {
        dao = new CompanyCarDao(databaseConnection, null);
        CompanyCar companyCar = (CompanyCar) dao.get(customerName);

        return companyCar;
    }
}
