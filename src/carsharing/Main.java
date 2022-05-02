package carsharing;

import carsharing.controller.UIManager;
import carsharing.database.DatabaseManager;
import carsharing.utils.FileChecker;

public class Main {

    public static void main(String[] args) {
//        String path = "./src/carsharing/db";
//        String extension = "db";
//        int folderLevel = 1;

//        if(!FileChecker.isFilePresent(path, extension, folderLevel)) {
//            System.out.println("Database file not found! Setting up database...");
//            DatabaseManager.setupDatabase(args, false);
//        } else {
//            System.out.println("Database file found!");
//        }
        DatabaseManager.setupDatabase(args, false);

        DatabaseManager.createTableStructure();

        UIManager uiManager = new UIManager();
        uiManager.manageUI(args);

    }
}