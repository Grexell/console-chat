package by.dima.model.logic;

import by.dima.model.entity.command.Command;
import by.dima.model.entity.command.LogCommand;

public class CommandBuilder {
    public static Command getCommand(String command){
        Command result;
        switch (command){
            default:
                result = new LogCommand();
        }
        return result;
    }
}
