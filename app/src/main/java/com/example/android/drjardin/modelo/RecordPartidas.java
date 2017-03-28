package com.example.android.drjardin.modelo;

/**
 * Created by Santiago Celada González on 19/03/2017.
 */

public class RecordPartidas implements java.io.Serializable {

    private Partida[] partidas;

    public Partida[] getPartidas() {
        return partidas;
    }

    public void setPartidas(Partida[] partidas) {
        this.partidas = partidas;
    }
}
