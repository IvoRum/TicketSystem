package com.diploma.ticket.system.domain.dto;

import java.time.LocalDateTime;
import java.util.List;

public record AuthenticationView (
        String id,

        UserView creator,
        LocalDateTime createdAt,

        String fullName,
        String about,
        String nationality,
        List<String> genres
) {

}