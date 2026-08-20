package com.cdantas.league.MatchEvent.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record MatchEventRegister(
        @NotBlank String type,
        @PositiveOrZero Integer minute,
        Long teamId,
        Long playerId,
        String description
) {}