package com.velus.lab6.common.request;

import com.velus.lab6.common.types.Authentication;

public class GetInfoRequest extends Request {
    public GetInfoRequest(Authentication authentication) {
        super(authentication);
    }
}
