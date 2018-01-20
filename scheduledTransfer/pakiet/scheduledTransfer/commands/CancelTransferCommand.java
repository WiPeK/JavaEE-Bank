package pakiet.scheduledTransfer.commands;

import pakiet.scheduledTransfer.interfaces.Command;
import pakiet.scheduledTransfer.ScheduledTransfer;

/**
 * Created by Michał on 20.01.2018.
 */
public class CancelTransferCommand implements Command {

    private ScheduledTransfer scheduledTransfer;

    public CancelTransferCommand(ScheduledTransfer scheduledTransfer) {
        this.scheduledTransfer = scheduledTransfer;
    }

    @Override
    public void execute() {

        scheduledTransfer.cancelTransfer();
    }
}
