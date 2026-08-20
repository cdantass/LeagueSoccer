package com.cdantas.league.Team.Dto;

import jakarta.validation.constraints.*;

public record TeamRegister(
        @NotBlank(message = "Nome é obrigatório") String name,
        @NotBlank @Size(max = 10, message = "Abreviação deve ter no máximo 10 caracteres") String abbreviation,
        @NotBlank(message = "Cidade é obrigatória") String city,
        @NotNull @Min(value = 1850, message = "Ano de fundação inválido") @Max(value = 2100) Integer foundedYear
) {}