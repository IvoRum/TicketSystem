package com.diploma.ticket.system.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketCreationRequest {
    private Long id;
    private String name;
    private Time workStart;
    private Time workEnd;
    private Long favorId;
    private Long typeId;
}
