package com.diploma.ticket.system.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.sql.Time;
@Data
@AllArgsConstructor
@Builder
@Setter
public class NextInLineResponse {
    private Long number;
    private Time finishTime;
    private Time issueTime;
    private int peopleInLine;
}
