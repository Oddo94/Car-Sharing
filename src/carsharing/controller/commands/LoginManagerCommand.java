package carsharing.controller.commands;

import carsharing.model.enums.MenuType;

public class LoginManagerCommand implements Command {

    private Receiver receiver;

    public LoginManagerCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public int execute() {
        int executionResult = receiver.displayMenu(MenuType.MANAGER_MENU);

        return executionResult;
    }
}
