package carsharing.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseOperations {
    private Connection databaseConnection;

    public DatabaseOperations(Connection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public int createTable(String sqlStatement) {
        if (sqlStatement == null || !sqlStatement.contains("CREATE TABLE"))  {
            return -1;
        }

        try (Statement stmt = databaseConnection.createStatement()) {

            stmt.execute(sqlStatement);
            return 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return -1;
    }

    public int dropTable(String tableName) {
        if(tableName == null) {
            return -1;
        }

        String dropTableStatement = String.format("DROP TABLE IF EXISTS %s", tableName);

        try (Statement dropStatement = databaseConnection.createStatement()) {

            dropStatement.execute(dropTableStatement);
            return 0;

        } catch(SQLException ex) {
            ex.printStackTrace();
        }

        return -1;
    }
}
