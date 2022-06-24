package com.velus.lab6.common.response;

import com.velus.lab6.common.types.Authentication;

public class LoginResponse extends Response {
    private final Authentication authentication;

    public LoginResponse(Authentication authentication) {
        this.authentication = authentication;
    }

    public Authentication getAuthentication() {
        return authentication;
    }
}
