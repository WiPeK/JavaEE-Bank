package pakiet.scheduledTransfer.commands;

import pakiet.scheduledTransfer.interfaces.Command;
import pakiet.scheduledTransfer.ScheduledTransfer;

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