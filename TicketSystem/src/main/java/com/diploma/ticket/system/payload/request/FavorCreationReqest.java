package com.diploma.ticket.system.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FavorCreationReqest {
    private String name;
    private String description;
    private Time workStart;
    private Time workEnd;
    private List<Long> idsOfTypeOfFavors;
}
