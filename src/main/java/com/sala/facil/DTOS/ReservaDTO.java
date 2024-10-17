package com.sala.facil.DTOS;

import jakarta.validation.constraints.NotNull;


import java.time.LocalDateTime;

public record ReservaDTO(Long id_reserva, @NotNull LocalDateTime data_reserva, @NotNull LocalDateTime data_pedido,
                         @NotNull int status, @NotNull Long sala_id, @NotNull Long usuario_id) {
}
