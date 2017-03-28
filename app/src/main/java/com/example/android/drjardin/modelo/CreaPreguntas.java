package com.example.android.drjardin.modelo;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Santiago Celada González on 22/03/2017.
 */

public class CreaPreguntas {

    public ArrayList<Pregunta> damePreguntas(){
        ArrayList<Pregunta> listaPreguntas = new ArrayList<>();
        Pregunta pregunta;
        String[] respuestas = null;
        int[] correctas = null;
        Enunciados enunciado = new Enunciados();
        Respuestas respuesta = new Respuestas();
        Correctas correcta = new Correctas();
        int contadorRespuestas = 0;
        int contadorCorrectas = 0;
        for(int n= 0;n<15;n++){
            pregunta = new Pregunta();
            if(n<5){
                pregunta.setEnunciado(enunciado.dameEnunciado(n));
                pregunta.setNumRespuestas(2);
                correctas = new int[1];
                correctas[0] = correcta.dameCorrecta(contadorCorrectas);
                contadorCorrectas++;
                pregunta.setRespuestaCorrecta(correctas);
                respuestas = new String[2];
                for(int j=0;j<2;j++){
                    respuestas[j] = respuesta.dameRespuesta(contadorRespuestas);
                    contadorRespuestas++;
                }
                pregunta.setRespuestas(respuestas);
            } else if(n>=5 & n<10){
                pregunta.setEnunciado(enunciado.dameEnunciado(n));
                pregunta.setNumRespuestas(4);
                correctas = new int[1];
                correctas[0] = correcta.dameCorrecta(contadorCorrectas);
                contadorCorrectas++;
                pregunta.setRespuestaCorrecta(correctas);
                respuestas = new String[4];
                for(int j=0;j<4;j++){
                    respuestas[j] = respuesta.dameRespuesta(contadorRespuestas);
                    contadorRespuestas++;
                }
                pregunta.setRespuestas(respuestas);
            } else if (n>=10 & n<15){
                pregunta.setEnunciado(enunciado.dameEnunciado(n));
                correctas = new int[2];
                correctas[0] = correcta.dameCorrecta(contadorCorrectas);
                contadorCorrectas++;
                correctas[1] = correcta.dameCorrecta(contadorCorrectas);
                contadorCorrectas++;
                pregunta.setRespuestaCorrecta(correctas);
                pregunta.setNumRespuestas(4);
                respuestas = new String[4];
                for(int k=0;k<4;k++){
                    respuestas[k] = respuesta.dameRespuesta(contadorRespuestas);
                    contadorRespuestas++;
                }
                pregunta.setRespuestas(respuestas);
            }
            listaPreguntas.add(pregunta);
        }
        return listaPreguntas;
    }
}
