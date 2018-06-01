package com.example.nobre.ntrack.modelo;

import java.io.Serializable;

public class Autodromo implements Serializable {

    private Long id;
    private String nome;
    private String cidade;
    private String estado;
    private String percurso;
    private String site;
    private Double nota;

    public Autodromo() {
    }

    public Autodromo(String nome, String cidade, String estado, String percurso, String site) {
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.percurso = percurso;
        this.site = site;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPercurso() {
        return percurso;
    }

    public void setPercurso(String percurso) {
        this.percurso = percurso;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
