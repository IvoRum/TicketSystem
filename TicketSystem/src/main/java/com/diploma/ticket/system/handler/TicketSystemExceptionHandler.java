package com.diploma.ticket.system.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class TicketSystemExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(
            IllegalArgumentException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Argument is not correct for this request"+ex);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(
            NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElement(
            NoSuchElementException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", "Id or Name of an entity is invalid!"+ex);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
//IllegalStateException
    @ExceptionHandler(
            IllegalStateException.class)
    public ResponseEntity<Object> handleIllegalState(
            IllegalStateException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", "The state of the request is invalid."+ex);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
