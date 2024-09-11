package ru.panic.springsteamauthexample.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.panic.springsteamauthexample.exception.AuthException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AuthHandlingController {

    @ExceptionHandler(value = AuthException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handlerAuthException(HttpServletRequest request, AuthException exception) {
        Map<String, String> body = new HashMap<>();

        body.put("error", "Bad request");
        body.put("message", exception.getMessage());
        body.put("path", request.getServletPath());

        return body;
    }
}
