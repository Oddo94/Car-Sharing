package carsharing.controller.commands;

public class CompanyMenuCommand implements Command {
    private Receiver receiver;
    private String companyName;

    public CompanyMenuCommand(Receiver receiver, String companyName) {
        this.receiver = receiver;
        this.companyName = companyName;
    }


    @Override
    public int execute() {
        int executionResult = receiver.displayCompanyMenu(companyName);
        return 0;
    }
}
