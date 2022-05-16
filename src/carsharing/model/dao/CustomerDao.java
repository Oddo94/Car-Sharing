package carsharing.model.dao;

import carsharing.model.Customer;
import carsharing.model.dto.CustomerDto;
import lombok.AllArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
public class CustomerDao implements Dao<Customer>{

    private String getAllCustomerStatement = "SELECT ID, NAME FROM CUSTOMER";
    private String insertCustomerStatement = "INSERT INTO CUSTOMER(NAME) VALUES(?)";
    private String updateCustomerRentedCarStatement = "UPDATE CUSTOMER " +
            "SET RENTED_CAR_ID = (SELECT ID FROM CAR WHERE NAME = ?) " +
            "WHERE NAME = ?";
    private String hasRentedCarStatement = "SELECT RENTED_CAR_ID FROM CUSTOMER WHERE NAME = ?";
//    private String setRentedCarStatement = "UPDATE CUSTOMER " +
//            "SET NAME = ?, RENTED_CAR_ID = ? " +
//            "WHERE NAME = ?";
private String setRentedCarStatement = "UPDATE CUSTOMER " +
        "SET RENTED_CAR_ID = ? " +
        "WHERE NAME = ?";
    //private String customerName;
    private String carName;
    private Connection databaseConnection;

//    public CustomerDao(Connection databaseConnection, String customerName, String carName) {
//        this.databaseConnection = databaseConnection;
//        this.customerName = customerName;
//        this.carName = carName;
//    }

    public CustomerDao(Connection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }


    @Override
    public Customer get(long id) {
        return null;
    }

    @Override
    public int getItemId(String itemName) {
        return -1;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customerList = new ArrayList<>();

        try(Statement statement = databaseConnection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getAllCustomerStatement);

            while(resultSet.next()) {
                Customer customer = new Customer();
                customer.setName(resultSet.getString(2));

                customerList.add(customer);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return customerList;
    }

    @Override
    public int save(Customer customer) {
        if(customer == null) {
            return -1;
        }

        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(insertCustomerStatement)) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.execute();

            return 0;
        } catch(SQLException ex) {
            ex.printStackTrace();
        }

        return -1;
    }

    @Override
    public int update(long id, Customer customer) {
        if(customer == null) {
            return -1;
        }

        //Checks if the customer has already rented a car and if he doesn't want to return a rented car
        if(hasRentedCar(customer.getName()) && customer.getRentedCarId() != 0) {
            return -1;
        }

        try (PreparedStatement preparedStatementUpdate = databaseConnection.prepareStatement(setRentedCarStatement)) {

            //preparedStatementUpdate.setString(1, customer.getName());
            //Used for returning the car(null values means that the respective user hasn't rented any car)
            if(customer.getRentedCarId() == 0) {
                preparedStatementUpdate.setNull(1, Types.INTEGER);
            } else {
                //Normal car rental flow
                preparedStatementUpdate.setInt(1, customer.getRentedCarId());
            }

            preparedStatementUpdate.setString(2, customer.getName());

            preparedStatementUpdate.execute();

            return 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return -1;

}

    @Override
    public int delete(long id) {
        return 0;
    }

    private boolean hasRentedCar(String customerName) {
        try (PreparedStatement preparedStatementCheckRental = databaseConnection.prepareStatement(hasRentedCarStatement)
            ) {

            preparedStatementCheckRental.setString(1, customerName);

            ResultSet resultSet = preparedStatementCheckRental.executeQuery();

            boolean wasNull = true;
            while(resultSet.next()) {
                int carId = resultSet.getInt(1);
               wasNull = resultSet.wasNull();//checks if the int column contained a null value
            }

            //The user hasn't rented any cars yet if the column value is null
            if(!wasNull) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

}
