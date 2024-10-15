package com.sala.facil.DTOS;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(@NotBlank String nome, @NotBlank String email, @NotBlank String phone, @NotBlank String cpf) {
}