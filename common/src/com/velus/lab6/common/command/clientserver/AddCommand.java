package com.velus.lab6.common.command.clientserver;

import com.velus.lab6.common.interpreter.InterpreterLoop;
import com.velus.lab6.common.util.IOManager;
import com.velus.lab6.common.valuereader.complex.WorkerReader;
import com.velus.lab6.common.request.AddRequest;
import com.velus.lab6.common.response.singleelement.AddResponse;
import com.velus.lab6.common.types.Worker;

public class AddCommand extends ClientServerCommand<AddResponse> {

    public AddCommand(InterpreterLoop interpreterLoop) {
        super(interpreterLoop);
    }

    @Override
    public String getDescription() {
        return "{element} : добавить новый элемент в коллекцию";
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    protected AddRequest buildRequest(IOManager ioManager) {
        Worker worker = new WorkerReader(ioManager).setNullable(false).read();
        return new AddRequest(getAuthentication(), worker);
    }

    @Override
    protected void onSuccess(IOManager ioManager, AddResponse response) {
        Worker element = response.getElement();
        ioManager.writeLine("Добавлен объект: " + element.toString());
    }

}
