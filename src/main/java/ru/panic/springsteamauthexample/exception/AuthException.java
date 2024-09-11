package ru.panic.springsteamauthexample.exception;

public class AuthException extends RuntimeException {
    public AuthException(String message) {
        super(message);
    }
}
