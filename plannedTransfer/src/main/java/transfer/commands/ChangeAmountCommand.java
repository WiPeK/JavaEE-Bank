package transfer.commands;

import transfer.interfaces.Command;
import transfer.classes.ScheduledTransfer;

/**
 * Created by Michał on 20.01.2018.
 */
public class ChangeAmountCommand implements Command {

    private ScheduledTransfer scheduledTransfer;

    public ChangeAmountCommand(ScheduledTransfer scheduledTransfer) {
        this.scheduledTransfer = scheduledTransfer;
    }

    @Override
    public void execute() {
        scheduledTransfer.changeAmount();
    }
}
