package com.example.android.drjardin.modelo;

/**
 * Created by Santiago Celada González on 22/03/2017.
 */

public class Correctas {
    private int[] correcta = new int[20];
    public Correctas(){
        correcta[0] = 2;
        correcta[1] = 1;
        correcta[2] = 2;
        correcta[3] = 1;
        correcta[4] = 2;
        correcta[5] = 4;
        correcta[6] = 1;
        correcta[7] = 3;
        correcta[8] = 1;
        correcta[9] = 4;
        correcta[10] = 1;
        correcta[11] = 3;
        correcta[12] = 3;
        correcta[13] = 4;
        correcta[14] = 1;
        correcta[15] = 2;
        correcta[16] = 2;
        correcta[17] = 3;
        correcta[18] = 2;
        correcta[19] = 3;
    }
    public int dameCorrecta(int indice){
        return correcta[indice];
    }
}
