package carsharing.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static String DATABASE_URL = "jdbc:h2:./src/carsharing/db/%s";

    public static Connection getDatabaseConnection() {
        Connection databaseConnection = null;

        try {
            Class.forName(JDBC_DRIVER);
            databaseConnection = DriverManager.getConnection(DATABASE_URL);
            databaseConnection.setAutoCommit(true);

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }

        return databaseConnection;
    }

    //Method for setting up the DB
    public static void setupDatabase(String[] inputArguments, boolean dropExistingTable) {
        if (inputArguments == null) {
            return;
        }
        String defaultDatabaseName = "carsharing";

        //Sets the database name based on the arguments received from the user
        if (inputArguments.length == 2 && "-databaseFileName".equals(inputArguments[0])) {
            setDatabaseName(inputArguments[1]);
        } else {
            setDatabaseName(defaultDatabaseName);
        }
    }

    public static int createTableStructure() {
        String tableName = "COMPANY";
        DatabaseOperations databaseOperations = new DatabaseOperations(getDatabaseConnection());

        String createCompanyTableStatement = "CREATE TABLE IF NOT EXISTS COMPANY(" +
                "ID INT(20) IDENTITY NOT NULL," +
                "NAME VARCHAR(50) NOT NULL UNIQUE," +
                "PRIMARY KEY(ID))";

        String createCarTableStatement = "CREATE TABLE IF NOT EXISTS CAR (" +
                "ID INT(20) IDENTITY NOT NULL," +
                "NAME VARCHAR(50) NOT NULL UNIQUE," +
                "COMPANY_ID INT(20) NOT NULL," +
                "PRIMARY KEY(ID)," +
                "FOREIGN KEY(COMPANY_ID) REFERENCES COMPANY(ID))";

        String createCustomerTableStatement = "CREATE TABLE IF NOT EXISTS CUSTOMER(" +
                "ID INT(20) IDENTITY NOT NULL," +
                "NAME VARCHAR(50) NOT NULL UNIQUE," +
                "RENTED_CAR_ID INT(20) DEFAULT NULL," +
                "PRIMARY KEY(ID)," +
                "FOREIGN KEY(RENTED_CAR_ID) REFERENCES CAR(ID))";


        //Creates a new table if it doesn't already exist
        int companyTblCreationResult = databaseOperations.createTable(createCompanyTableStatement);
        int carTblCreationResult = databaseOperations.createTable(createCarTableStatement);
        int customerTblCreationResult = databaseOperations.createTable(createCustomerTableStatement);


        if(companyTblCreationResult == -1 || carTblCreationResult == -1 || customerTblCreationResult == -1) {
            return -1;
        }

        return 0;
    }

    private static void setDatabaseName(String databaseName) {
        DATABASE_URL = String.format(DATABASE_URL, databaseName);
    }
}
