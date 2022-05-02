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

//        String tableName = "COMPANY";
//        DatabaseOperations databaseOperations = new DatabaseOperations(getDatabaseConnection());
//
//        int dropResult;
//        if (dropExistingTable) {
//            //Drops the existing table
//            dropResult = databaseOperations.dropTable(tableName);
//        }
//
//        String createTableStatement = "CREATE TABLE IF NOT EXISTS COMPANY(" +
//                "ID INT(10) IDENTITY NOT NULL PRIMARY KEY," +
//                "NAME VARCHAR(50) NOT NULL UNIQUE)";
//
//        //Creates a new table if it doesn't already exist
//        int creationResult = databaseOperations.createTable(createTableStatement);
    }

    public static int createTableStructure() {
        String tableName = "COMPANY";
        DatabaseOperations databaseOperations = new DatabaseOperations(getDatabaseConnection());

        String createTableStatement = "CREATE TABLE IF NOT EXISTS COMPANY(" +
                "ID INT(10) IDENTITY NOT NULL PRIMARY KEY," +
                "NAME VARCHAR(50) NOT NULL UNIQUE)";

        //Creates a new table if it doesn't already exist
        int creationResult = databaseOperations.createTable(createTableStatement);

        return creationResult;
    }

    private static void setDatabaseName(String databaseName) {
        DATABASE_URL = String.format(DATABASE_URL, databaseName);
    }
}
