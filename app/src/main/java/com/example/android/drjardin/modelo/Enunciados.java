package com.example.android.drjardin.modelo;

/**
 * Created by Santiago Celada González on 22/03/2017.
 */

public class Enunciados {
    String[] enunciado = new String[15];
    public Enunciados(){
        enunciado[0] = "¿Cúal es el océano más\ngrande del mundo?";
        enunciado[1] = "¿A qué velocidad se \nmueve el sistema solar?";
        enunciado[2] = "¿Podría el ser humano\nvivir sin las plantas?";
        enunciado[3] = "¿La aspirina se extrae\nde las plantas?";
        enunciado[4] = "¿Qué elemento es más común\nen el planeta tierra?";
        enunciado[5] = "¿Cuánto mide el ser vivo\nmás grande del planeta?";
        enunciado[6] = "¿Cuántos años tiene la planta\nviva más antigua del planeta?";
        enunciado[7] = "¿Cuándo aparecieron las primeras\nplantas sobre el planeta?";
        enunciado[8] = "¿Dónde está la planta más antigua\ndel planeta?";
        enunciado[9] = "¿Cúantas especies de plantas\nantícancerígenas se conocen?";
        enunciado[10] = "De las siguientes plantas:\n¿cuales son acuáticas?";
        enunciado[11] = "De los siguientes árboles:\n¿cuales son de hoja caduca?";
        enunciado[12] = "De estos países:\n¿indica los que producen café?";
        enunciado[13] = "De estos insectos:\n¿cuales son herbívoros?";
        enunciado[14] = "De los siguientes animales:\n¿Cúales están en peligro de extinción?";
    }

    public String dameEnunciado(int indice){
        return enunciado[indice];
    }



}
