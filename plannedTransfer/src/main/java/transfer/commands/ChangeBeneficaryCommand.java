package transfer.commands;

import transfer.classes.ScheduledTransfer;
import transfer.interfaces.Command;

/**
 * Created by Michał on 20.01.2018.
 */
public class ChangeBeneficaryCommand implements Command {

    private ScheduledTransfer scheduledTransfer;

    public ChangeBeneficaryCommand(ScheduledTransfer scheduledTransfer) {
        this.scheduledTransfer = scheduledTransfer;
    }
    @Override
    public void execute() {

        scheduledTransfer.changeBeneficary();
}

}