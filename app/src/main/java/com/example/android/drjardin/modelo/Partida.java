package com.example.android.drjardin.modelo;

import java.sql.Date;

/**
 * Created by Santiago Celada González on 13/03/2017.
 */

public class Partida implements java.io.Serializable{

    private int idPartida;
    private int[] puntos;
    private String[] nombreUsuario;
    private Date fecha;

    public int getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }

    public String[] getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String[] nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int[] getPuntos() {
        return puntos;
    }

    public void setPuntos(int[] puntos) {
        this.puntos = puntos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}

