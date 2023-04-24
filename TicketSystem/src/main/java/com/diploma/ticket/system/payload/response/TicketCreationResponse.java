package com.diploma.ticket.system.payload.response;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
@Setter
public class TicketCreationResponse {
    private Long id;
    private String massage;
}
