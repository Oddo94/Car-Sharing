package carsharing.model.dao;

import carsharing.model.CompanyCar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyCarDao implements Dao<CompanyCar> {
    private Connection databaseConnection;
    private String companyName;

    private String getCompanyCars = "SELECT COMPANY.NAME, CAR.NAME " +
                                    "FROM COMPANY " +
                                    "INNER JOIN CAR ON COMPANY.ID = CAR.COMPANY_ID " +
                                    "WHERE COMPANY.NAME = ? ";
                                    //"AND CAR.ID NOT IN (SELECT RENTED_CAR_ID FROM CUSTOMER)";



    public CompanyCarDao(Connection databaseConnection, String companyName) {
        this.databaseConnection = databaseConnection;
        this.companyName = companyName;
    }
    @Override
    public CompanyCar get(long id) {
        return null;
    }

    @Override
    public int getItemId(String itemName) {
        return -1;
    }

    @Override
    public List<CompanyCar> getAll() {
        List<CompanyCar> companyCarList = new ArrayList<CompanyCar>();

        try (PreparedStatement preparedStatement = databaseConnection.prepareStatement(getCompanyCars) ){
            preparedStatement.setString(1, companyName);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String companyName = resultSet.getString(1);
                String carName = resultSet.getString(2);

                CompanyCar companyCar = new CompanyCar(companyName, carName);

                companyCarList.add(companyCar);

            }

        } catch(SQLException ex) {
            ex.printStackTrace();
        }

        return companyCarList;
    }

    @Override
    public int save(CompanyCar companyCar) {
        return 0;
    }

    @Override
    public int update(long id, CompanyCar companyCar) {
        return 0;
    }

    @Override
    public int delete(long id) {
        return 0;
    }
}
