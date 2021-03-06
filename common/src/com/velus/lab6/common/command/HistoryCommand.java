package com.velus.lab6.common.command;

import com.velus.lab6.common.interpreter.InterpreterData;
import com.velus.lab6.common.interpreter.InterpreterLoop;
import com.velus.lab6.common.util.IOManager;

import java.util.List;

public class HistoryCommand extends Command {

    public HistoryCommand(InterpreterLoop interpreterLoop) {
        super(interpreterLoop);
    }

    @Override
    public void onExecute(IOManager ioManager) {
        InterpreterData interpreterData = getInterpreterData();
        List<String> history = interpreterData.getHistory(6);
        for (String entry : history) {
            ioManager.writeLine(entry);
        }
    }

    @Override
    public String getDescription() {
        return ": вывести последние 6 команд (без их аргументов)";
    }

    @Override
    public String getName() {
        return "history";
    }
}
