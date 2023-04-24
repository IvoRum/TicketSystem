package com.diploma.ticket.system.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
@Builder
@Setter
public class FavorCreationResponce {
    private Long id;
    private String massage;
}
