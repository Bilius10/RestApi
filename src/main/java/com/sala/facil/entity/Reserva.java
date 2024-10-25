package com.sala.facil.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
public class Reserva implements Serializable {

    @Serial
    private static final long serialVersionUID = 87654345L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_reserva;

    private LocalDateTime data_reserva;

    private LocalDateTime data_pedido;

    private boolean status;

    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false)
    private Sala sala;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Reserva(Long id_reserva, LocalDateTime data_reserva, LocalDateTime data_pedido, boolean status, Sala sala, Usuario usuario) {
        this.id_reserva = id_reserva;
        this.data_reserva = data_reserva;
        this.data_pedido = data_pedido;
        this.status = status;
        this.sala = sala;
        this.usuario = usuario;
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

    public void setId_reserva(Long idReserva) {
        this.id_reserva = idReserva;
    }

    public Long getId_reserva() {
        return id_reserva;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id_reserva=" + id_reserva +
                ", data_reserva=" + data_reserva +
                ", data_pedido=" + data_pedido +
                ", status=" + status +
                ", sala=" + sala +
                ", usuario=" + usuario +
                '}';
    }
}
