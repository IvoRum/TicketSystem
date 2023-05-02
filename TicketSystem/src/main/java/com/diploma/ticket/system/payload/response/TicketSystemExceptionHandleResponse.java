package com.diploma.ticket.system.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@Setter
public class TicketSystemExceptionHandleResponse {
    private LocalDateTime timestamp;
    private String message;
    private String exeptionMessage;
}
