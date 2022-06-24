package com.velus.lab6.client;

import com.velus.lab6.common.command.LoginCommand;
import com.velus.lab6.common.command.RegisterCommand;
import com.velus.lab6.common.command.Command;
import com.velus.lab6.common.command.CommandFactory;

import java.util.List;

public class ClientCommandFactory extends CommandFactory {
    @Override
    protected void addCommandClasses(List<Class<? extends Command>> commandClasses) {
        super.addCommandClasses(commandClasses);
        commandClasses.add(RegisterCommand.class);
        commandClasses.add(LoginCommand.class);
    }
}
