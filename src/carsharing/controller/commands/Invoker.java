package carsharing.controller.commands;

public class Invoker {

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public int executeCommand() {
        return command.execute();
    }
}
