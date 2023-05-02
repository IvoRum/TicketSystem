package com.diploma.ticket.system.handler;

import com.diploma.ticket.system.payload.response.TicketSystemExceptionHandleResponse;
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
    public ResponseEntity<TicketSystemExceptionHandleResponse> handleIllegalArgument(
            IllegalArgumentException ex, WebRequest request
    ) {

        TicketSystemExceptionHandleResponse responseBody=
                 TicketSystemExceptionHandleResponse.builder()
                         .message("Argument is not correct for this request")
                         .timestamp( LocalDateTime.now())
                         .exeptionMessage(ex.toString())
                         .build();


        return ResponseEntity.badRequest().body(responseBody);
    }

    @ExceptionHandler(
            NoSuchElementException.class)
    public ResponseEntity<TicketSystemExceptionHandleResponse> handleNoSuchElement(
            NoSuchElementException ex, WebRequest request
    ) {

        TicketSystemExceptionHandleResponse responseBody=
                TicketSystemExceptionHandleResponse.builder()
                        .message("Id or Name of an entity is invalid!")
                        .timestamp( LocalDateTime.now())
                        .exeptionMessage(ex.toString())
                        .build();


        return ResponseEntity.badRequest().body(responseBody);
    }

    @ExceptionHandler(
            IllegalStateException.class)
    public ResponseEntity<TicketSystemExceptionHandleResponse> handleIllegalState(
            IllegalStateException ex, WebRequest request) {
        TicketSystemExceptionHandleResponse responseBody=
                TicketSystemExceptionHandleResponse.builder()
                        .message("The state of the request is invalid!")
                        .timestamp( LocalDateTime.now())
                        .exeptionMessage(ex.toString())
                        .build();


        return ResponseEntity.badRequest().body(responseBody);
    }
}
