package by.dima.model.logic;

import by.dima.model.entity.command.ChatCommand;
import by.dima.model.entity.command.LogCommand;
import by.dima.model.entity.command.RegisterCommand;
import by.dima.model.entity.command.UnregisterCommand;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandBuilderTest {

    @Test
    public void getCommand() {
        assertThat(CommandBuilder.getCommand(CommandBuilder.EXIT_COMMAND), new IsInstanceOf(UnregisterCommand.class));
        assertThat(CommandBuilder.getCommand(CommandBuilder.MESSAGE_COMMAND), new IsInstanceOf(ChatCommand.class));
        assertThat(CommandBuilder.getCommand(CommandBuilder.REGISTER_COMMAND), new IsInstanceOf(RegisterCommand.class));
        assertThat(CommandBuilder.getCommand(CommandBuilder.UNREGISTER_COMMAND), new IsInstanceOf(UnregisterCommand.class));
        assertThat(CommandBuilder.getCommand("test"), new IsInstanceOf(LogCommand.class));
    }
}