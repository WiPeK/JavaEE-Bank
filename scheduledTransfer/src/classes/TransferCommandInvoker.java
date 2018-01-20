package classes;

import interfaces.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michał on 20.01.2018.
 */
public class TransferCommandInvoker {

    List<Command> commandList = new ArrayList<Command>();

    public void addCommand(Command command){

        commandList.add(command);
    }

    public void invoke(){

        for(Command c: commandList){

            c.execute();
        }

    }

}
