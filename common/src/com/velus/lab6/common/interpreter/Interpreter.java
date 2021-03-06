package com.velus.lab6.common.interpreter;

import com.velus.lab6.common.application.Application;
import com.velus.lab6.common.command.CommandFactory;

public class Interpreter {

    private final Application application;
    private final InterpreterData interpreterData = new InterpreterData();
    private final CommandFactory commandFactory;

    public Interpreter(Application application, CommandFactory commandFactory) {
        this.application = application;
        this.commandFactory = commandFactory;
    }

    public void run() {
        InterpreterLoop loop = new InterpreterLoop(this);
        loop.run();
    }

    public Application getApplication() {
        return application;
    }

    public InterpreterData getData() {
        return interpreterData;
    }

    public CommandFactory getCommandFactory() {
        return commandFactory;
    }
}
