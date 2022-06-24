package com.velus.lab6.common.request;

import com.velus.lab6.common.types.Authentication;

public class LoginRequest extends Request {
    public LoginRequest(Authentication authentication) {
        super(authentication);
    }
}
