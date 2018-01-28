package transfer.commands;

import transfer.classes.ScheduledTransfer;
import transfer.interfaces.Command;

/**
 * Created by Michał on 28.01.2018.
 */
public class CancelTransferCommand  implements Command{

    private ScheduledTransfer scheduledTransfer;

    public CancelTransferCommand(ScheduledTransfer scheduledTransfer) {
        this.scheduledTransfer = scheduledTransfer;
    }
    @Override
    public void execute() {

        scheduledTransfer.cancelTransfer();
    }

}
