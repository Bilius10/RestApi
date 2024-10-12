package com.sala.facil.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_reserva;
    private LocalDateTime data_reserva;
    private LocalDateTime data_pedido;
    private int status;
    private int sala_id;
    private int usuario_id;

    public Reserva(Long id_reserva, LocalDateTime data_reserva, LocalDateTime data_pedido, int status, int sala_id, int usuario_id) {
        this.id_reserva = id_reserva;
        this.data_reserva = data_reserva;
        this.data_pedido = data_pedido;
        this.status = status;
        this.sala_id = sala_id;
        this.usuario_id = usuario_id;
    }

    public Reserva() {
    }

    public LocalDateTime getData_reserva() {
        return data_reserva;
    }

    public void setData_reserva(LocalDateTime data_reserva) {
        this.data_reserva = data_reserva;
    }

    public LocalDateTime getData_pedido() {
        return data_pedido;
    }

    public void setData_pedido(LocalDateTime data_pedido) {
        this.data_pedido = data_pedido;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSala_id() {
        return sala_id;
    }

    public void setSala_id(int sala_id) {
        this.sala_id = sala_id;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public void setId_reserva(Long idReserva) {
        this.id_reserva = idReserva;
    }

    public Long getId_reserva() {
        return id_reserva;
    }
}
