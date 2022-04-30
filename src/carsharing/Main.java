package carsharing;

import carsharing.controller.UIManager;

public class Main {

    public static void main(String[] args) {
//        String createTableStatement  = "CREATE TABLE IF NOT EXISTS COMPANY(" +
//                "ID INT(10) NOT NULL AUTO_INCREMENT" +
//                "NAME VARCHAR(50) NOT NULL UNIQUE," +
//                "PRIMARY KEY(ID))";
//
//        setDatabaseName(args);
//
//        DatabaseOperations databaseOperations = new DatabaseOperations(DatabaseManager.getDatabaseConnection());
//        int executionResult = databaseOperations.createTable(createTableStatement);

        UIManager uiManager = new UIManager();
        uiManager.manageUI(args);


    }

//    private static void setDatabaseName(String[] args) {
//        if(args.length == 2 && "-databaseFileName".equals(args[0])) {
//            DatabaseManager.setDatabaseName(args[1]);
//        } else {
//            DatabaseManager.setDatabaseName("carsharing");
//        }

}