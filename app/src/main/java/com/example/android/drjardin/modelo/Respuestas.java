package com.example.android.drjardin.modelo;

/**
 * Created by Santiago Celada González on 22/03/2017.
 */

public class Respuestas {
    String[] respuesta = new String[50];
    public Respuestas(){
        respuesta[0] = "Atlántico";
        respuesta[1] = "Pacífico";
        respuesta[2] = "790.000 Km/h";
        respuesta[3] = "3.200 Km/h";
        respuesta[4] = "Sí";
        respuesta[5] = "No";
        respuesta[6] = "Sí";
        respuesta[7] = "No";
        respuesta[8] = "Tierra";
        respuesta[9] = "Agua";
        respuesta[10] = "4 mtrs";
        respuesta[11] = "40 mtrs";
        respuesta[12] = "400 mtrs";
        respuesta[13] = "4 km";
        respuesta[14] = "100.000 años";
        respuesta[15] = "1000 años";
        respuesta[16] = "10.000 años";
        respuesta[17] = "1.500 años";
        respuesta[18] = "1'5 mill. años";
        respuesta[19] = "345.000 años";
        respuesta[20] = "470 mill. años";
        respuesta[21] = "124 mill. años";
        respuesta[22] = "España";
        respuesta[23] = "Brasil";
        respuesta[24] = "EE.UU.";
        respuesta[25] = "Japón";
        respuesta[26] = "785";
        respuesta[27] = "1.580";
        respuesta[28] = "150.000";
        respuesta[29] = "35.000";
        respuesta[30] = "Arroz";
        respuesta[31] = "Mangle";
        respuesta[32] = "Nenufar";
        respuesta[33] = "Alcachofa";
        respuesta[34] = "Pino";
        respuesta[35] = "Limonero";
        respuesta[36] = "Almendro";
        respuesta[37] = "Ciruelo";
        respuesta[38] = "España";
        respuesta[39] = "Brasil";
        respuesta[40] = "Noruega";
        respuesta[41] = "Canadá";
        respuesta[42] = "Mariquita";
        respuesta[43] = "Grillo";
        respuesta[44] = "Saltamontes";
        respuesta[45] = "Mantis religiosa";
        respuesta[46] = "Hiena";
        respuesta[47] = "Oso polar";
        respuesta[48] = "Canguro";
        respuesta[49] = "Coyote";
    }

    public String dameRespuesta(int indice){
        return respuesta[indice];
    }
}
