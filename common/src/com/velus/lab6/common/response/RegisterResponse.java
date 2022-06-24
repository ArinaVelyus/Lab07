package com.velus.lab6.common.response;

import com.velus.lab6.common.types.Authentication;

public class RegisterResponse extends Response {
    private final Authentication authentication;

    public RegisterResponse(Authentication authentication) {
        this.authentication = authentication;
    }

    public Authentication getAuthentication() {
        return authentication;
    }
}
