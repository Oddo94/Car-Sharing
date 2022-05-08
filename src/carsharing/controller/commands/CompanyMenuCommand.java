package carsharing.controller.commands;

public class CompanyMenuCommand implements Command {
    private Receiver receiver;
    private String companyName;
    private boolean isFirstDisplay;


    public CompanyMenuCommand(Receiver receiver, String companyName, boolean isFirstDisplay) {
        this.receiver = receiver;
        this.companyName = companyName;
        this.isFirstDisplay = isFirstDisplay;
    }


    @Override
    public int execute() {
        int executionResult = receiver.displayCompanyMenu(companyName, isFirstDisplay);
        return 0;
    }
}
