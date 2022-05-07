package carsharing.controller.commands;

public class CarListCommand implements Command {
    private Receiver receiver;
    String companyName;

    public CarListCommand(Receiver receiver, String companyName) {
        this.receiver = receiver;
        this.companyName = companyName;
    }

    @Override
    public int execute() {
        int executionResult = receiver.displayCompanyCars(companyName);

        return executionResult;
    }
}
