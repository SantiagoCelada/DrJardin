package com.example.android.drjardin.modelo;

/**
 * Created by Santiago Celada González on 22/03/2017.
 */

public class Pregunta {

    String enunciado;
    int numRespuestas;
    String[] respuestas;
    int[] correctas;

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public int getNumRespuestas() {
        return numRespuestas;
    }

    public void setNumRespuestas(int numRespuestas) {
        this.numRespuestas = numRespuestas;
    }

    public String[] getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(String[] respuestas) {
        this.respuestas = respuestas;
    }

    public int[] getRespuestaCorrecta() {
        return correctas;
    }

    public void setRespuestaCorrecta(int[] correctas) {
        this.correctas = correctas;
    }
}
