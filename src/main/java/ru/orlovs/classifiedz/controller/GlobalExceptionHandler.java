package ru.orlovs.classifiedz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.orlovs.classifiedz.controller.dto.ErrorMessage;

import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AuthenticationException.class)
    public Set<ErrorMessage> handleAuthExceptions(AuthenticationException ex, Locale locale) {
        Set<ErrorMessage> result = new HashSet<>();

        String msg = messageSource.getMessage(ex.getMessage(), null, locale);
        result.add(new ErrorMessage(msg));

        return result;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Set<ErrorMessage> handleValidationExceptions(MethodArgumentNotValidException ex, Locale locale) {
        Set<ErrorMessage> result = new HashSet<>();

        ex.getBindingResult().getAllErrors().forEach((err) -> {
            if (err.getDefaultMessage() != null) {
                String field = ((FieldError) err).getField();
                String msg = messageSource.getMessage(err.getDefaultMessage(), null, locale);
                result.add(new ErrorMessage(msg, field));
            }
        });

        return result;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public Set<ErrorMessage> handleAccessDeniedException(AccessDeniedException ex, Locale locale) {
        String msg = messageSource.getMessage(ex.getMessage(), null, locale);
        return Collections.singleton(new ErrorMessage(msg));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Set<ErrorMessage> handleOtherExceptions(Exception ex, Locale locale) {
        String msg = messageSource.getMessage(ex.getMessage(), null, locale);
        return Collections.singleton(new ErrorMessage(msg));
    }
}
