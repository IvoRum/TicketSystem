package com.diploma.ticket.system.payload.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FavorWhitTicketRequest {
    FavorCreationReqest favorCreationReqest;
    TicketCreationRequest ticketCreationRequest;
}
