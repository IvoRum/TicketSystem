package com.diploma.ticket.system.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MachineCreationRequest {
    private String name;
    private String type;
    private Long favorId;
}
