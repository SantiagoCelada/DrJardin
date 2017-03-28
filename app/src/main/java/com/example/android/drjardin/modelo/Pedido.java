package com.example.android.drjardin.modelo;

import java.util.Date;

/**
 * Created by Santiago Celada González on 19/03/2017.
 */

public class Pedido implements java.io.Serializable {

    private int numPedido;
    private Persona persona;
    private Date fecha;
    private Producto[] productos;

    public int getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(int numPedido) {
        this.numPedido = numPedido;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Producto[] getProductos() {
        return productos;
    }

    public void setProductos(Producto[] productos) {
        this.productos = productos;
    }
}
