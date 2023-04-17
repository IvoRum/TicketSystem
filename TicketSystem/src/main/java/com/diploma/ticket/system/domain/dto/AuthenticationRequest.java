package com.diploma.ticket.system.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record AuthenticationRequest (
    @NotNull
    @Email String username,
    @NotNull String password) {

  public AuthenticationRequest() {
            this(null, null);
        }
    }

