package com.velus.lab6.server.request;

import com.velus.lab6.common.request.Request;
import com.velus.lab6.common.types.Authentication;
import com.velus.lab6.common.types.Worker;

public class RemoveLowerRequest extends Request {
    private final Worker element;

    public RemoveLowerRequest(Authentication authentication, Worker element) {
        super(authentication);
        this.element = element;
    }

    public Worker getElement() {
        return element;
    }
}
