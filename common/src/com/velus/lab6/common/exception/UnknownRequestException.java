package com.velus.lab6.common.exception;

public class UnknownRequestException extends RequestFailureException {
    public UnknownRequestException() {
        super("Ошибка: не удаётся определить тип запроса.");
    }
}