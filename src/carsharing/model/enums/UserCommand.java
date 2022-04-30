package carsharing.model.enums;

public enum UserCommand {
    LOG_IN_AS_MANAGER(1, "log in as manager"),
    DISPLAY_COMPANY_LIST(1, "Company list"),
    CREATE_COMPANY(2, "Create company"),
    BACK(0, "Back"),
    EXIT(0, "Exit");



    private int commandNumber;
    private String commandName;

    UserCommand(int commandNumber, String commandName) {
        this.commandNumber = commandNumber;
        this.commandName = commandName;
    }
}
