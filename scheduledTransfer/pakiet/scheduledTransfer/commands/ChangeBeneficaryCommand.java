package pakiet.scheduledTransfer.commands;

import pakiet.scheduledTransfer.interfaces.Command;
import pakiet.scheduledTransfer.ScheduledTransfer;

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
