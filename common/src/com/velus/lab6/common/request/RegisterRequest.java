package com.velus.lab6.common.request;

import com.velus.lab6.common.types.Authentication;

public class RegisterRequest extends Request {
    public RegisterRequest(Authentication authentication) {
        super(authentication);
    }
}
