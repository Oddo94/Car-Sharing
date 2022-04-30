package carsharing.controller.commands;

import carsharing.repository.CarSharingRepository;

public interface Command {

    int execute();
}
