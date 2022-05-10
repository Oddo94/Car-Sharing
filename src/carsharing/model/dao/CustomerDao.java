package carsharing.model.dao;

import carsharing.model.Customer;
import carsharing.model.dto.CustomerDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao implements Dao<Customer>{

    private String getAllCustomerStatement = "SELECT ID, NAME FROM CUSTOMER";
    private String insertCustomerStatement = "INSERT INTO CUSTOMER(NAME) VALUES(?)";
    private Connection databaseConnection;

    public CustomerDao(Connection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public Customer get(long id) {
        return null;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customerList = new ArrayList<>();

        try(Statement statement = databaseConnection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getAllCustomerStatement);

            while(resultSet.next()) {
                Customer customer = new Customer();
                customer.setName(resultSet.getString(1));

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
            preparedStatement.execute();

            return 0;
        } catch(SQLException ex) {
            ex.printStackTrace();
        }

        return -1;
    }

    @Override
    public int update(long id, Customer customer) {
        return 0;
    }

    @Override
    public int delete(long id) {
        return 0;
    }
}
