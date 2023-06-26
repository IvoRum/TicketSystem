package com.diploma.ticket.system.payload.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class FavorWhitTicketResponse {
    private Long favorId;
    private Long ticketId;
}
