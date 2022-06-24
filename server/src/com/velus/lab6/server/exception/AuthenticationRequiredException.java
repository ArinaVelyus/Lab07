package com.velus.lab6.server.exception;

public class AuthenticationRequiredException extends RuntimeException {

    public AuthenticationRequiredException() {
        super("Требуется аутентификация.");
    }
}
