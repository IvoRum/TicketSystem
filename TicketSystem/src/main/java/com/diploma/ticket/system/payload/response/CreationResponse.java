package com.diploma.ticket.system.payload.response;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
@Setter
public class CreationResponse {
    private Long id;
    private String massage;
}
