package com.velus.lab6.server;

import com.velus.lab6.common.command.Command;
import com.velus.lab6.common.command.CommandFactory;
import com.velus.lab6.server.command.ClearCommand;
import com.velus.lab6.server.command.RemoveHeadCommand;
import com.velus.lab6.server.command.RemoveLowerCommand;

import java.util.List;

public class ServerCommandFactory extends CommandFactory {
    @Override
    protected void addCommandClasses(List<Class<? extends Command>> commandClasses) {
        super.addCommandClasses(commandClasses);
        commandClasses.add(ClearCommand.class);
        commandClasses.add(RemoveHeadCommand.class);
        commandClasses.add(RemoveLowerCommand.class);
    }
}
