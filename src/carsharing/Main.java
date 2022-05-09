package carsharing;

import carsharing.controller.UIManager;
import carsharing.database.DatabaseManager;
import carsharing.utils.FileChecker;

public class Main {

    public static void main(String[] args) {
        DatabaseManager.setupDatabase(args, false);

        DatabaseManager.createTableStructure();

        UIManager uiManager = new UIManager();
        uiManager.manageUI(args);

    }
}