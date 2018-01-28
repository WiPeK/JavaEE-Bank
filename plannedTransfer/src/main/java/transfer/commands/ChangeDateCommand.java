package transfer.commands;

import transfer.classes.ScheduledTransfer;
import transfer.interfaces.Command;

/**
 * Created by Michał on 20.01.2018.
 */
public class ChangeDateCommand implements Command {

    private ScheduledTransfer scheduledTransfer;

    public ChangeDateCommand(ScheduledTransfer scheduledTransfer) {
        this.scheduledTransfer = scheduledTransfer;
    }

    @Override
    public void execute() {
        scheduledTransfer.changeDate();
    }
}
