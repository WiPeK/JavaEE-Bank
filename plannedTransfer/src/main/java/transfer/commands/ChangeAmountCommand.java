package transfer.commands;

import transfer.classes.ScheduledTransfer;
import transfer.interfaces.Command;

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
