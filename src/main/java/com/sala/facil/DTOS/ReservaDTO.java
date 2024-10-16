package com.sala.facil.DTOS;

import jakarta.validation.constraints.NotNull;


import java.time.LocalDateTime;

public record ReservaDTO(long id_reserva, @NotNull LocalDateTime data_reserva, @NotNull LocalDateTime data_pedido,
                         @NotNull int status, @NotNull int sala_id, @NotNull int usuario_id) {
}
