package com.velus.lab6.server.request;

import com.velus.lab6.common.request.Request;
import com.velus.lab6.common.types.Authentication;

public class ClearRequest extends Request {
    public ClearRequest(Authentication authentication) {
        super(authentication);
    }
}
