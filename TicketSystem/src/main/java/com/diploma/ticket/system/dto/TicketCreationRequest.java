package com.diploma.ticket.system.dto;

import com.diploma.ticket.system.entity.Favor;
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
    private String name;
    private Time workStart;
    private Time workEnd;
    private Favor favor;
}
