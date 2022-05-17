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
    //private String customerName;

    //Add condition to filter the rented car from the displayed list
    private String getCompanyCars = "SELECT COMPANY.NAME, CAR.NAME " +
                                    "FROM COMPANY " +
                                    "INNER JOIN CAR ON CAR.COMPANY_ID = COMPANY.ID " +
                                    "WHERE COMPANY.NAME = ? " +
                                    "AND CAR.ID NOT IN (SELECT RENTED_CAR_ID FROM CUSTOMER WHERE RENTED_CAR_ID IS NOT NULL)";

    private String getCompanyRentedCar = "SELECT COMPANY.NAME, CAR.NAME " +
                                        "FROM CUSTOMER " +
                                        "INNER JOIN CAR ON CUSTOMER.RENTED_CAR_ID = CAR.ID " +
                                        "INNER JOIN COMPANY ON CAR.COMPANY_ID = COMPANY.ID " +
                                        "WHERE CUSTOMER.NAME = ?";



//    private String getCompanyCars = "      SELECT car.id, car.name, car.company_id " +
//            " FROM car LEFT JOIN customer" +
//            "ON car.id = customer.rented_car_id" +
//            "WHERE customer.name IS NULL";



    public CompanyCarDao(Connection databaseConnection, String companyName) {
        this.databaseConnection = databaseConnection;
        this.companyName = companyName;
    }

    @Override
    public CompanyCar get(String itemName) {
        CompanyCar companyCar = null;

        try(PreparedStatement preparedStatement = databaseConnection.prepareStatement(getCompanyRentedCar)) {
            preparedStatement.setString(1, itemName);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String companyName = resultSet.getString(1);
                String carName = resultSet.getString(2);

                companyCar = new CompanyCar(companyName, carName);
            }

        } catch(SQLException ex) {
            ex.printStackTrace();
        }

        return companyCar;
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

                //if(rentedCarId != 0) {
                    CompanyCar companyCar = new CompanyCar(companyName, carName);

                    companyCarList.add(companyCar);
                //}

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
