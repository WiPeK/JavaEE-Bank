package commands;

import interfaces.Command;
import classes.ScheduledTransfer;

/**
 * Created by Michał on 20.01.2018.
 */
public class ChangeSenderCommand implements Command {

    private ScheduledTransfer scheduledTransfer;

    public ChangeSenderCommand(ScheduledTransfer scheduledTransfer) {
        this.scheduledTransfer = scheduledTransfer;
    }

    @Override
    public void execute() {

        scheduledTransfer.changeSender();

    }
}