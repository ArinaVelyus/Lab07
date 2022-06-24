package com.velus.lab6.server.request;

import com.velus.lab6.common.request.Request;
import com.velus.lab6.common.types.Authentication;

public class RemoveHeadRequest extends Request {
    public RemoveHeadRequest(Authentication authentication) {
        super(authentication);
    }
}
