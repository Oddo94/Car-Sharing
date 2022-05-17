package carsharing.repository;

import carsharing.model.Car;
import carsharing.model.Company;
import carsharing.model.CompanyCar;
import carsharing.model.Customer;
import carsharing.model.dao.*;

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

    public int insertCompany(Company company) {
        CompanyDao companyDao = new CompanyDao(databaseConnection);

        //Company company = new Company(companyDto.getName());

        int insertionResult = companyDao.save(company);

        return insertionResult;
    }

    public List<Car> getAllCompanyCars(String companyName) {
        CarDao carDao = new CarDao(databaseConnection, companyName);

        List<Car> carList = carDao.getAll();

        if(carList.size() == 0) {
            return new ArrayList<Car>();
        }

//        List<CarDto> resultList = carList
//                .stream()
//                .map(car -> new CarDto(car.getId(), car.getName()))
//                .sorted(Comparator.comparing(carDto -> carDto.getId()))
//                .collect(Collectors.toList());

        return carList;

    }

    public int insertCar(Car car, String companyName) {
        CarDao carDao = new CarDao(databaseConnection, companyName);

        int insertionResult = carDao.save(car);

        return insertionResult;
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

    public int setRentedCarForCustomer(Customer customer) {
        CustomerDao customerDao = new CustomerDao(databaseConnection);

        //Checks if the user tries to rent a new car before returning the current one
        if(customerDao.hasRentedCar(customer.getName()) && customer.getRentedCarId() != 0) {
            System.out.println("\nYou have already rented a car!");
            return -1;
        }
//        if(customerDao.hasRentedCar(customer.getName())) {
//            return -1;
//        }

        int carRentalResult =  customerDao.update(0L, customer);

        return carRentalResult;
    }

    public int returnRentedCar(Customer customer) {
        CustomerDao customerDao = new CustomerDao(databaseConnection);

        //Checks if the user tries to return a car without previously renting one
       if(!customerDao.hasRentedCar(customer.getName())) {
           System.out.println("\nYou didn't rent a car!");
           return -1;
        } else if(customerDao.hasRentedCar(customer.getName())) {
//           System.out.println("\nYou have already rented a car!");
//           return -1;
       }

        int executionResult = customerDao.update(0l, customer);

        return executionResult;
    }

    public CompanyCar getCustomerRentedCar(String customerName) {
        dao = new CompanyCarDao(databaseConnection, null);
        CompanyCar companyCar = (CompanyCar) dao.get(customerName);

        return companyCar;
    }

    public boolean hasRentedCar(String customerName) {
        CustomerDao customerDao = new CustomerDao(databaseConnection);

        return customerDao.hasRentedCar(customerName);
    }
}
