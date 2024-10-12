package com.sala.facil.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_sala;
    private String nome;
    private String departamento;
    private String descricao;
    private int status;

    public Sala(Long id_sala, String nome, String departamento, String descricao, int status) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setId_sala(Long idSala) {
        this.id_sala = idSala;
    }

    public Long getId_sala() {
        return id_sala;
    }
}
