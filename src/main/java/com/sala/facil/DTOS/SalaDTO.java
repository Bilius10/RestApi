package com.sala.facil.DTOS;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SalaDTO(@NotBlank String nome, @NotBlank String departamento, @NotBlank String descricao, @NotNull int status) {
}
