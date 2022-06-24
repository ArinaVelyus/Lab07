package com.velus.lab6.common.response.singleelement;

import com.velus.lab6.common.response.Response;
import com.velus.lab6.common.types.Worker;

public abstract class SingleElementResponse extends Response {
    private final Worker element;

    public SingleElementResponse(Worker element) {
        this.element = element;
    }

    public Worker getElement() {
        return element;
    }
}
