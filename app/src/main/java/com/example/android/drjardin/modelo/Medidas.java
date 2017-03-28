package com.example.android.drjardin.modelo;

import android.app.Application;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.RelativeLayout;

/**
 * Created by Santiago Celada González on 07/03/2017.
 */

public class Medidas{

    private Application app;
    private int orientacion;
    private int ancho;
    private int alto;
    private int anchoIconos;
    private int altoIconos;
    private int margenIconos;

    public Medidas(Application app){

        this.app = app;
        DisplayMetrics metrics = app.getResources().getDisplayMetrics();
        ancho = metrics.widthPixels;
        alto = metrics.heightPixels;
        orientacion =  app.getResources().getConfiguration().orientation;

    }

    public void dameMedidasMenuInicio(RelativeLayout maps,RelativeLayout mapsBandera,RelativeLayout whatsup,RelativeLayout whatsupBandera,RelativeLayout internet,RelativeLayout internetBandera){

        int margenTopBandera;
        int altoBandera;
        int margenLeftBandera;

        if(orientacion==1){
            anchoIconos = (int) Math.round(ancho * 0.1875);
            altoIconos = anchoIconos;
            margenIconos = (int) Math.round(ancho * 0.04);
            altoBandera = (int) Math.round(altoIconos * 0.85);
            margenTopBandera = (int) Math.round(margenIconos * 1.3);
            margenLeftBandera = (int) Math.round(margenIconos * 1.5);
            cambiaMedidasIconos(maps,anchoIconos,altoIconos,margenIconos,margenIconos,0,0);
            cambiaMedidasIconos(mapsBandera,ancho,altoBandera,margenLeftBandera,margenTopBandera,margenTopBandera,0);
            cambiaMedidasIconos(whatsup,anchoIconos,altoIconos,margenIconos,margenIconos,0,0);
            cambiaMedidasIconos(whatsupBandera,ancho,altoBandera,margenLeftBandera,margenTopBandera,margenTopBandera,0);
            cambiaMedidasIconos(internet,anchoIconos,altoIconos,margenIconos,margenIconos,0,0);
            cambiaMedidasIconos(internetBandera,ancho,altoBandera,margenLeftBandera,margenTopBandera,margenTopBandera,0);
            Log.i("Java","El anchoIcono es: "+anchoIconos);
            Log.i("Java","El margenIcono es: "+margenIconos);
            Log.i("Java","El altoBandera es: "+altoBandera);
            Log.i("Java","El margenTopBandera es: "+margenTopBandera);

        } else {
            anchoIconos = (int) Math.round(alto * 0.1875);
            altoIconos = anchoIconos;
            margenIconos = (int) Math.round(alto * 0.04);
            altoBandera = (int) Math.round(altoIconos * 0.85);
            margenTopBandera = (int) Math.round(margenIconos * 1.3);
            margenLeftBandera = Math.round(ancho/8);
            cambiaMedidasIconos(maps,anchoIconos,altoIconos,(margenIconos*9),margenIconos,0,0);
            cambiaMedidasIconos(mapsBandera,ancho,altoBandera,margenLeftBandera,margenTopBandera,margenTopBandera,0);
            cambiaMedidasIconos(whatsup,anchoIconos,altoIconos,(margenIconos*9),margenIconos,0,0);
            cambiaMedidasIconos(whatsupBandera,ancho,altoBandera,margenLeftBandera,margenTopBandera,margenTopBandera,0);
            cambiaMedidasIconos(internet,anchoIconos,altoIconos,(margenIconos*9),margenIconos,0,0);
            cambiaMedidasIconos(internetBandera,ancho,altoBandera,margenLeftBandera,margenTopBandera,margenTopBandera,0);
        }
    }

    public void dameMedidasRedesSociales(RelativeLayout faceBook, RelativeLayout googlePlus, RelativeLayout twiter, RelativeLayout youTube){

        Log.i("Java","La orientación es: "+ orientacion);
        Log.i("Java","El ancho es: "+ancho);
        Log.i("Java","El alto es: "+alto);

        if(orientacion==1) {
            anchoIconos = ((Math.round(ancho * 70 / 100))/4);
            altoIconos = anchoIconos;
            margenIconos = ((Math.round(ancho * 30  / 100))/5);
            cambiaMedidasIconos(faceBook,anchoIconos,altoIconos,margenIconos,margenIconos,0,margenIconos);
            cambiaMedidasIconos(googlePlus,anchoIconos,altoIconos,margenIconos,margenIconos,0,margenIconos);
            cambiaMedidasIconos(twiter,anchoIconos,altoIconos,margenIconos,margenIconos,0,margenIconos);
            cambiaMedidasIconos(youTube,anchoIconos,altoIconos,margenIconos,margenIconos,0,margenIconos);
        } else{
            anchoIconos = (Math.round(ancho /9));
            altoIconos = anchoIconos;
            margenIconos = (Math.round(ancho /9));
            cambiaMedidasIconos(faceBook,anchoIconos,altoIconos,margenIconos,(margenIconos/2),0,(margenIconos/2));
            cambiaMedidasIconos(googlePlus,anchoIconos,altoIconos,margenIconos,(margenIconos/2),0,(margenIconos/2));
            cambiaMedidasIconos(twiter,anchoIconos,altoIconos,margenIconos,(margenIconos/2),0,(margenIconos/2));
            cambiaMedidasIconos(youTube,anchoIconos,altoIconos,margenIconos,(margenIconos/2),0,(margenIconos/2));
        }

    }

    private void cambiaMedidasIconos(RelativeLayout imagen,int anchoIcono, int altoIcono, int margenLeft,int margenTop,int margenRight,int margenBottom){

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imagen.getLayoutParams();
        imagen.getLayoutParams().height = altoIcono;
        imagen.getLayoutParams().width = anchoIcono;
        layoutParams.setMargins(margenLeft,margenTop,margenRight,margenBottom);
        imagen.setLayoutParams(layoutParams);

    }


}
