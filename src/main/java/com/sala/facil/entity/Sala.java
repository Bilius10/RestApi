package com.sala.facil.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
public class Sala implements Serializable {

    @Serial
    private static final long serialVersionUID = 54654554L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_sala;

    private String nome;

    private String departamento;

    private String descricao;

    private boolean status;

    @OneToMany(mappedBy = "sala")
    @JsonIgnore
    private List<Reserva> reservas;

    public Sala(Long id_sala, String nome, String departamento, String descricao, boolean status) {
        this.id_sala = id_sala;
        this.nome = nome;
        this.departamento = departamento;
        this.descricao = descricao;
        this.status = status;
    }

    public Sala() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setId_sala(Long idSala) {
        this.id_sala = idSala;
    }

    public Long getId_sala() {
        return id_sala;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reserva) {
        this.reservas = reserva;
    }

}
