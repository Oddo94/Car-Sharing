package carsharing.controller.commands;

import carsharing.repository.CarSharingRepository;

public class CompanyListCommand implements Command {
    private Receiver receiver;

    public CompanyListCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public int execute() {
        int executionResult = receiver.displayCompanyList();

        return executionResult;
    }
}
