package com.diploma.ticket.system.dto;

import com.diploma.ticket.system.entity.Favor;
import lombok.*;

import java.sql.Time;
@Data
@AllArgsConstructor
@Builder
@Setter
public class TicketCreationResponse {
    private Long id;
    private String massage;
}
