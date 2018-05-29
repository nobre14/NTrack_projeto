package com.example.nobre.n_track.modelo;

import java.io.Serializable;

public class Treino implements Serializable{
    private Long id;
    private String data;
    private String autodromo;
    private String melhorTempo;
    private String setup;
    private String velocidadeMaxima;
    private int numVoltas;

    public Treino() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAutodromo() {
        return autodromo;
    }

    public void setAutodromo(String autodromo) {
        this.autodromo = autodromo;
    }

    public String getMelhorTempo() {
        return melhorTempo;
    }

    public void setMelhorTempo(String melhorTempo) {
        this.melhorTempo = melhorTempo;
    }

    public String getSetup() {
        return setup;
    }

    public void setSetup(String setup) {
        this.setup = setup;
    }

    public String getVelocidadeMaxima() {
        return velocidadeMaxima;
    }

    public void setVelocidadeMaxima(String velocidadeMaxima) {
        this.velocidadeMaxima = velocidadeMaxima;
    }

    public int getNumVoltas() {
        return numVoltas;
    }

    public void setNumVoltas(int numVoltas) {
        this.numVoltas = numVoltas;
    }

    @Override
    public String toString() {
        return autodromo + " " + data;
    }
}
