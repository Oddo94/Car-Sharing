package carsharing.model.dao;

import carsharing.model.Company;
import carsharing.model.dto.CompanyDto;

import java.sql.*;
import java.util.*;

public class CompanyDao implements Dao<Company>{

    private String insertCompanyStatement = "INSERT INTO COMPANY(NAME) VALUES(?)";
    private String getAllCompaniesStatement = "SELECT ID, NAME FROM COMPANY";

    private Connection databaseConnection;

    public CompanyDao(Connection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }


    @Override
    public Company get(long id) {
        return null;
    }

    @Override
    public List<Company> getAll() {
        List<Company> companiesList = new ArrayList<>();

        try(Statement statement = databaseConnection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(getAllCompaniesStatement);

            while(resultSet.next()) {
                int id = resultSet.getInt(1);
                String companyName = resultSet.getString(2);

               Company company = new Company(id, companyName);
               companiesList.add(company);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return companiesList;
    }

    @Override
    public int save(Company company) {
        if(company == null) {
            return -1;
        }

       try(PreparedStatement preparedStatement = databaseConnection.prepareStatement(insertCompanyStatement)) {
           preparedStatement.setString(1, company.getName());
           preparedStatement.execute();

           return 0;

       } catch(SQLException ex) {
           System.err.println(ex.getMessage());
       }

       return -1;
    }

    @Override
    public int update(long id, Company company) {
        return -1;
    }

    @Override
    public int delete(long id) {
        return -1;
    }
}
