package com.example.android.drjardin;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.example.android.drjardin.modelo.CreaPreguntas;
import com.example.android.drjardin.modelo.Partida;
import com.example.android.drjardin.modelo.Persona;
import com.example.android.drjardin.modelo.Pregunta;
import com.example.android.drjardin.modelo.ServicioWeb;
import com.example.android.drjardin.modelo.toStringFromString;

import org.ksoap2.serialization.SoapPrimitive;

import java.io.IOException;
import java.util.ArrayList;

public class JuegoActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView mensaje,botonOne, botonTwo, botonThree, infoJugador1, infoJugador2, infoJugador3, infoJugador4;
    private ImageButton imageButton1, imageButton2, imageButton3, imageButton4;
    private ViewAnimator extrasView, jugadorCentral;
    private ProgressBar progressBar;
    private Chronometer crono;
    private boolean jugador0IsDead = false;
    private boolean jugador1IsDead = false;
    private boolean jugador2IsDead = false;
    private boolean jugador3IsDead = false;
    private ExtrasTurno extras;
    private Partida partida = new Partida();
    private CreaPreguntas creaPreguntas;
    private ArrayList<Pregunta> listadoPreguntas;
    private Pregunta pregunta;
    private String[] respuestas;
    private int[] correctas;
    private int numeroJugadores = ScrollingActivity.numJugadores;
    private String nombreUsuario[] = new String[ScrollingActivity.numJugadores];
    private int puntos[] = new int[ScrollingActivity.numJugadores];
    private TextView nombreUsuario1, nombreUsuario2, nombreUsuario3, nombreUsuario4, puntosUsuario1, puntosUsuario2, puntosUsuario3, puntosUsuario4, preguntaText, textBoton1, textBoton2, textBoton3, textBoton4,botonOneText;
    private RadioGroup radioGroup;
    private RadioButton radioButton1, radioButton2;
    private CheckBox check1, check2, check3, check4;
    private LinearLayout panelMensajes,panelExtras, boton2Radio, check, boton4;
    private RelativeLayout botonTwoView;
    private int extraActual, jugadorActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE);
        declareFields();
        if(ScrollingActivity.jugador1 == null){
            ScrollingActivity.jugador1 = dameAnonimo();
        }
        dameListener();
        damePartida();
        damePanel(View.GONE,View.VISIBLE,View.GONE,View.GONE,View.GONE,View.GONE,View.GONE);
    }

    private void pasaTurno() {
        infoJugador1.setVisibility(View.INVISIBLE);
        infoJugador2.setVisibility(View.INVISIBLE);
        infoJugador3.setVisibility(View.INVISIBLE);
        infoJugador4.setVisibility(View.INVISIBLE);
        switch (jugadorActual) {
            case 3:
                if (jugador0IsDead && jugador1IsDead && jugador2IsDead) {

                } else if (jugador0IsDead && jugador1IsDead && !jugador2IsDead) {
                    jugadorActual = 2;
                    jugadorCentral.showNext();
                    jugadorCentral.showNext();
                    jugadorCentral.showNext();
                } else if (jugador0IsDead && !jugador1IsDead) {
                    jugadorActual = 1;
                    jugadorCentral.showNext();
                    jugadorCentral.showNext();
                } else if (!jugador0IsDead) {
                    jugadorActual = 0;
                    jugadorCentral.showNext();
                }
                break;
            case 2:
                if (jugador3IsDead && jugador0IsDead && jugador1IsDead) {

                } else if (jugador3IsDead && jugador0IsDead && !jugador1IsDead) {
                    jugadorActual = 1;
                    jugadorCentral.showNext();
                    jugadorCentral.showNext();
                    jugadorCentral.showNext();
                } else if (jugador3IsDead && !jugador0IsDead) {
                    jugadorActual = 0;
                    jugadorCentral.showNext();
                    jugadorCentral.showNext();
                } else if (!jugador3IsDead) {
                    jugadorActual = 3;
                    jugadorCentral.showNext();
                }
                break;
            case 1:
                if (jugador2IsDead && jugador3IsDead && jugador0IsDead) {

                } else if (jugador2IsDead && jugador3IsDead && !jugador0IsDead) {
                    jugadorActual = 0;
                    jugadorCentral.showNext();
                    jugadorCentral.showNext();
                    jugadorCentral.showNext();
                } else if (jugador2IsDead && !jugador3IsDead) {
                    jugadorActual = 3;
                    jugadorCentral.showNext();
                    jugadorCentral.showNext();
                } else if (!jugador2IsDead) {
                    jugadorActual = 2;
                    jugadorCentral.showNext();
                }
                break;
            case 0:
                if (jugador1IsDead && jugador2IsDead && jugador3IsDead) {

                } else if (jugador1IsDead && jugador2IsDead && !jugador3IsDead) {
                    jugadorActual = 3;
                    jugadorCentral.showNext();
                    jugadorCentral.showNext();
                    jugadorCentral.showNext();
                }
                if (jugador1IsDead && !jugador2IsDead) {
                    jugadorActual = 2;
                    jugadorCentral.showNext();
                    jugadorCentral.showNext();
                } else if (!jugador1IsDead) {
                    jugadorActual = 1;
                    jugadorCentral.showNext();
                }
                break;
        }
    }

    private void dameInfoJugador(int imageId){
        switch (jugadorActual) {
            case 0:
                infoJugador1.setImageResource(imageId);
                infoJugador1.setVisibility(View.VISIBLE);
                break;
            case 1:
                infoJugador2.setImageResource(imageId);
                infoJugador2.setVisibility(View.VISIBLE);
                break;
            case 2:
                infoJugador3.setImageResource(imageId);
                infoJugador3.setVisibility(View.VISIBLE);
                break;
            case 3:
                infoJugador4.setImageResource(imageId);
                infoJugador4.setVisibility(View.VISIBLE);
                break;
        }

    }
    private void borraInfoJugadores(){
        infoJugador1.setVisibility(View.INVISIBLE);
        infoJugador2.setVisibility(View.INVISIBLE);
        infoJugador3.setVisibility(View.INVISIBLE);
        infoJugador4.setVisibility(View.INVISIBLE);
    }

    private void damePanel(int panelMensaje,int panelExtra,int cronoView,int preguntaTexto,int boton2radios,int checkBoxes,int botones4){
        panelMensajes.setVisibility(panelMensaje);
        panelExtras.setVisibility(panelExtra);
        crono.setVisibility(cronoView);
        preguntaText.setVisibility(preguntaTexto);
        boton2Radio.setVisibility(boton2radios);
        check.setVisibility(checkBoxes);
        boton4.setVisibility(botones4);
        if(panelMensajes.getVisibility() == View.VISIBLE){
            extrasView.setVisibility(View.GONE);
            botonOne.setVisibility(View.INVISIBLE);
            botonOneText.setVisibility(View.INVISIBLE);
        } else {
            extrasView.setVisibility(View.GONE);
            botonOne.setVisibility(View.VISIBLE);
            botonOneText.setVisibility(View.VISIBLE);
            botonTwoView.setVisibility(View.VISIBLE);
        }
    }

    private void damePreguntas(){
        extras.cancel();
        if(listadoPreguntas.size()==2){
            listadoPreguntas = creaPreguntas.damePreguntas();
        } else {}
        pregunta = listadoPreguntas.get((int) Math.round((listadoPreguntas.size()-1) * Math.random()));
        respuestas = pregunta.getRespuestas();
        preguntaText.setText(pregunta.getEnunciado());
        listadoPreguntas.remove(pregunta);
        if(pregunta.getNumRespuestas()==2){
            radioGroup.clearCheck();
            radioButton1.setText(respuestas[0]);
            radioButton2.setText(respuestas[1]);
            damePanel(View.GONE,View.GONE,View.VISIBLE,View.VISIBLE,View.VISIBLE,View.GONE,View.GONE);
        } else if (pregunta.getNumRespuestas()==4 && pregunta.getRespuestaCorrecta().length==2){
            check1.setChecked(false);
            check2.setChecked(false);
            check3.setChecked(false);
            check4.setChecked(false);
            check1.setText(respuestas[0]);
            check2.setText(respuestas[1]);
            check3.setText(respuestas[2]);
            check4.setText(respuestas[3]);
            damePanel(View.GONE,View.GONE,View.VISIBLE,View.VISIBLE,View.GONE,View.VISIBLE,View.GONE);
        } else if (pregunta.getNumRespuestas()==4 && pregunta.getRespuestaCorrecta().length==1) {
            textBoton1.setText(respuestas[0]);
            textBoton2.setText(respuestas[1]);
            textBoton3.setText(respuestas[2]);
            textBoton4.setText(respuestas[3]);
            damePanel(View.GONE,View.GONE,View.VISIBLE,View.VISIBLE,View.GONE,View.GONE,View.VISIBLE);
        }
        crono.setBase(SystemClock.elapsedRealtime());
        crono.start();
    }

    private void evaluaRespuestas(int viewId){

        correctas = pregunta.getRespuestaCorrecta();
        int[] respondida = new int[1];
        switch (viewId){
            case R.id.radio_boton1_text:
                respondida[0] = 1;
                break;
            case R.id.radio_boton2_text:
                respondida[0] = 2;
                break;
            case R.id.radio_group_botones:
                respuestaIncorrecta();
            case R.id.boton_two:
            case R.id.view_4respuestas_check:
                respondida = new int[2];
                if(check1.isChecked()){
                    respondida[0]=1;
                } else {}
                if(check2.isChecked() && respondida[0]>0){
                    respondida[1]=2;
                } else if(check2.isChecked() && respondida[0]==0){
                    respondida[0]=2;
                } else {}
                if(check3.isChecked() && respondida[0]>0){
                    respondida[1]=3;
                } else if (check3.isChecked() && respondida[0]==0){
                    respondida[0]=3;
                } else {}
                if(check4.isChecked() && respondida[0]>0){
                    respondida[1]=4;
                } else {}
                break;
            case R.id.respuesta1_image_button:
                respondida[0] = 1;
                break;
            case R.id.respuesta2_image_button:
                respondida[0] = 2;
                break;
            case R.id.respuesta3_image_button:
                respondida[0] = 3;
                break;
            case R.id.respuesta4_image_button:
                respondida[0] = 4;
                break;
            case R.id.view_4respuestas_boton:
                respuestaIncorrecta();
                break;
            default:
                break;
        }
        switch(respondida.length){
            case 1:
                if(respondida[0] == correctas[0]){
                    respuestaCorrecta();
                } else {
                    respuestaIncorrecta();
                }
                break;
            case 2:
                if(respondida[0] == correctas[0] & respondida[1] == correctas[1]){
                    respuestaCorrecta();
                } else {
                    respuestaIncorrecta();
                }
                break;
            default:
                respuestaIncorrecta();
                break;
        }

        damePanel(View.VISIBLE,View.VISIBLE,View.GONE,View.GONE,View.GONE,View.GONE,View.GONE);


    }

    private void respuestaCorrecta(){
        damePanelMensajes(R.drawable.correcto);
        switch (extraActual){
            case R.drawable.x2:
                puntos[jugadorActual] += 2;
                break;
            case R.drawable.x4:
                puntos[jugadorActual] += 4;
                break;
            case R.drawable.x5:
                puntos[jugadorActual] += 5;
                break;
            case R.drawable.extra:
                puntos[jugadorActual] += 1;
                break;
        }
        actualizaUsuario(jugadorActual);
    }

    private void respuestaIncorrecta(){

        ImageView vida1 = null;
        ImageView vida2 = null;
        ImageView vida3 = null;
        damePanelMensajes(R.drawable.fallo);
        switch (jugadorActual){
            case 0:
                vida1 = (ImageView) findViewById(R.id.j1_vida1);
                vida2 = (ImageView) findViewById(R.id.j1_vida2);
                vida3 = (ImageView) findViewById(R.id.j1_vida3);
                break;
            case 1:
                vida1 = (ImageView) findViewById(R.id.j2_vida1);
                vida2 = (ImageView) findViewById(R.id.j2_vida2);
                vida3 = (ImageView) findViewById(R.id.j2_vida3);
                break;
            case 2:
                vida1 = (ImageView) findViewById(R.id.j3_vida1);
                vida2 = (ImageView) findViewById(R.id.j3_vida2);
                vida3 = (ImageView) findViewById(R.id.j3_vida3);
                break;
            case 3:
                vida1 = (ImageView) findViewById(R.id.j4_vida1);
                vida2 = (ImageView) findViewById(R.id.j4_vida2);
                vida3 = (ImageView) findViewById(R.id.j4_vida3);
                break;
            default:
                break;
        }

        if(vida3.getVisibility() == View.VISIBLE){
            vida3.setVisibility(View.GONE);
        } else if(vida2.getVisibility() == View.VISIBLE){
            vida2.setVisibility(View.GONE);
        } else {
            vida1.setVisibility(View.GONE);
            switch (jugadorActual){
                case 0:
                    damePanelMensajes(R.drawable.fin);
                    jugador0IsDead = true;
                    numeroJugadores--;
                    break;
                case 1:
                    damePanelMensajes(R.drawable.fin);
                    jugador1IsDead = true;
                    numeroJugadores--;
                    break;
                case 2:
                    damePanelMensajes(R.drawable.fin);
                    jugador2IsDead = true;
                    numeroJugadores--;
                    break;
                case 3:
                    damePanelMensajes(R.drawable.fin);
                    jugador3IsDead = true;
                    numeroJugadores--;
                    break;
            }
        }

    }

    private void damePanelMensajes(int imagenMensaje){
        mensaje.setImageResource(imagenMensaje);
    }

    private void newActivity(Object object){

        Intent intent = new Intent(this, (Class<?>) object);
        startActivity(intent);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.boton_one:
                extrasView.setVisibility(View.VISIBLE);
                botonOne.setVisibility(View.INVISIBLE);
                botonOneText.setVisibility(View.INVISIBLE);
                botonTwoView.setVisibility(View.INVISIBLE);
                borraInfoJugadores();
                extras = new ExtrasTurno((long)(2000*Math.random()),50);
                extras.start();
                break;
            case R.id.radio_boton1_text:
            case R.id.radio_boton2_text:
            case R.id.boton_two:
            case R.id.respuesta1_image_button:
            case R.id.respuesta2_image_button:
            case R.id.respuesta3_image_button:
            case R.id.respuesta4_image_button:
                crono.stop();
                evaluaRespuestas(v.getId());
                break;
            case R.id.boton_three:
                if(extraActual == R.drawable.extra) {

                } else {
                    pasaTurno();
                }
                damePanel(View.GONE,View.VISIBLE,View.GONE,View.GONE,View.GONE,View.GONE,View.GONE);
                if(jugador0IsDead && jugador1IsDead && jugador2IsDead && jugador3IsDead){
                    GuardaRecords guardaRecords = new GuardaRecords();
                    RelativeLayout r = (RelativeLayout) findViewById(R.id.boton_one_view);
                    r.setVisibility(View.GONE);
                    r = (RelativeLayout) findViewById(R.id.boton_three_view);
                    r.setVisibility(View.GONE);
                    LinearLayout l = (LinearLayout) findViewById(R.id.panel_records);
                    l.setVisibility(View.VISIBLE);
                    guardaRecords.execute();
                }
                break;
            default:
                break;
        }
    }

    private class ExtrasTurno extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public ExtrasTurno(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            extrasView.showNext();
            extraActual = extrasView.getCurrentView().getId();

            if(extraActual == R.id.pierde_view && numeroJugadores==1) {
                extrasView.showNext();
            }

        }

        @Override
        public void onFinish() {
            extraActual = extrasView.getCurrentView().getId();
            switch (extraActual){
                case R.id.x2_view1:
                case R.id.x2_view2:
                case R.id.x2_view3:
                    extraActual = R.drawable.x2;
                    dameInfoJugador(extraActual);
                    break;
                case R.id.x4_view1:
                case R.id.x4_view2:
                    extraActual = R.drawable.x4;
                    dameInfoJugador(extraActual);
                    break;
                case R.id.x5_view:
                    extraActual = R.drawable.x5;
                    dameInfoJugador(extraActual);
                    break;
                case R.id.pierde_view:
                    extraActual = R.drawable.pierde;
                    pasaTurno();
                    break;
                case R.id.extra_view:
                    extraActual = R.drawable.extra;
                    dameInfoJugador(extraActual);
                    break;
            }
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(extraActual == R.drawable.pierde){
                damePanel(View.GONE,View.VISIBLE,View.GONE,View.GONE,View.GONE,View.GONE,View.GONE);
            } else {
                damePreguntas();
            }
        }
    }

    private class GuardaRecords extends AsyncTask<Void, Integer, String> {

        @Override
        protected String doInBackground(Void... params) {
            String mensaje = null;
            try {
                mensaje = toStringFromString.toString(partida);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String respuesta = null;
            ServicioWeb servicioWeb = new ServicioWeb("partidasUsuario");
            SoapPrimitive response = servicioWeb.llamadaServicio(mensaje);
            respuesta = response.toString();
            publishProgress(100);

            return respuesta;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0].intValue();
            progressBar.setProgress(progreso);
        }

        @Override
        protected void onPreExecute() {
            progressBar.setMax(100);
            progressBar.setProgress(0);
        }

        @Override
        protected void onPostExecute(String respuesta) {
            newActivity(MenuScoreActivity.class);
        }

    }

    private Persona dameAnonimo(){
        Persona anonimo = new Persona();
        anonimo.setNombre("ANONIMO");
        anonimo.setUsuario("ANONIMO");
        return anonimo;
    }

    private void damePartida(){

        switch (numeroJugadores){
            case 1:
                jugador1IsDead = true;
                jugador2IsDead = true;
                jugador3IsDead = true;
                nombreUsuario[0] = ScrollingActivity.jugador1.getUsuario();
                nombreUsuario1.setText(nombreUsuario[0]);
                puntos[0] = Integer.parseInt((String) puntosUsuario1.getText());
                actualizaUsuario(0);
                partida.setNombreUsuario(nombreUsuario);
                partida.setPuntos(puntos);
                borraUsuario(R.id.marcador_jugador2);
                borraUsuario(R.id.marcador_jugador3);
                borraUsuario(R.id.marcador_jugador4);
                break;
            case 2:
                jugador2IsDead = true;
                jugador3IsDead = true;
                nombreUsuario[0] = ScrollingActivity.jugador1.getUsuario();
                nombreUsuario1.setText(nombreUsuario[0]);
                nombreUsuario[1] = ScrollingActivity.jugador2.getUsuario();
                nombreUsuario2.setText(nombreUsuario[1]);
                puntos[0] = Integer.parseInt((String) puntosUsuario1.getText());
                actualizaUsuario(0);
                puntos[1] = Integer.parseInt((String) puntosUsuario2.getText());
                actualizaUsuario(1);
                partida.setNombreUsuario(nombreUsuario);
                partida.setPuntos(puntos);
                borraUsuario(R.id.marcador_jugador3);
                borraUsuario(R.id.marcador_jugador4);
                break;
            case 3:
                jugador3IsDead = true;
                nombreUsuario[0] = ScrollingActivity.jugador1.getUsuario();
                nombreUsuario1.setText(nombreUsuario[0]);
                nombreUsuario[1] = ScrollingActivity.jugador2.getUsuario();
                nombreUsuario2.setText(nombreUsuario[1]);
                nombreUsuario[2] = ScrollingActivity.jugador3.getUsuario();
                nombreUsuario3.setText(nombreUsuario[2]);
                puntos[0] = Integer.parseInt((String) puntosUsuario1.getText());
                actualizaUsuario(0);
                puntos[1] = Integer.parseInt((String) puntosUsuario2.getText());
                actualizaUsuario(1);
                puntos[2] = Integer.parseInt((String) puntosUsuario3.getText());
                actualizaUsuario(2);
                partida.setNombreUsuario(nombreUsuario);
                partida.setPuntos(puntos);
                borraUsuario(R.id.marcador_jugador4);
                break;
            case 4:
                nombreUsuario[0] = ScrollingActivity.jugador1.getUsuario();
                nombreUsuario1.setText(nombreUsuario[0]);
                nombreUsuario[1] = ScrollingActivity.jugador2.getUsuario();
                nombreUsuario2.setText(nombreUsuario[1]);
                nombreUsuario[2] = ScrollingActivity.jugador3.getUsuario();
                nombreUsuario3.setText(nombreUsuario[2]);
                nombreUsuario[3] = ScrollingActivity.jugador4.getUsuario();
                nombreUsuario4.setText(nombreUsuario[3]);
                puntos[0] = Integer.parseInt((String) puntosUsuario1.getText());
                actualizaUsuario(0);
                puntos[1] = Integer.parseInt((String) puntosUsuario2.getText());
                actualizaUsuario(1);
                puntos[2] = Integer.parseInt((String) puntosUsuario3.getText());
                actualizaUsuario(2);
                puntos[3] = Integer.parseInt((String) puntosUsuario4.getText());
                actualizaUsuario(3);
                partida.setNombreUsuario(nombreUsuario);
                partida.setPuntos(puntos);
                break;
        }
    }

    private void borraUsuario(int marcador){
        LinearLayout marcadorJugador = (LinearLayout) findViewById(marcador);
        marcadorJugador.setVisibility(View.GONE);
    }

    private void actualizaUsuario(int usuario){
        switch (usuario){
            case 0:
                puntosUsuario1.setText(String.valueOf(puntos[usuario]));
                break;
            case 1:
                puntosUsuario2.setText(String.valueOf(puntos[usuario]));
                break;
            case 2:
                puntosUsuario3.setText(String.valueOf(puntos[usuario]));
                break;
            case 3:
                puntosUsuario4.setText(String.valueOf(puntos[usuario]));
                break;
            default:
                break;
        }
    }

    private void declareFields() {
        botonOne = (ImageView) findViewById(R.id.boton_one);
        botonTwo = (ImageView) findViewById(R.id.boton_two);
        botonThree = (ImageView) findViewById(R.id.boton_three);
        creaPreguntas = new CreaPreguntas();
        listadoPreguntas = new ArrayList<>();
        listadoPreguntas = creaPreguntas.damePreguntas();
        crono = (Chronometer) findViewById(R.id.crono_view);
        progressBar = (ProgressBar) findViewById(R.id.progess_barra);
        extrasView = (ViewAnimator) findViewById(R.id.animator_extras);
        jugadorCentral = (ViewAnimator) findViewById(R.id.animator_jugadores);
        jugadorActual = 0;
        botonTwoView = (RelativeLayout) findViewById(R.id.boton_two_view);
        panelExtras = (LinearLayout) findViewById(R.id.panel_extras);
        panelMensajes = (LinearLayout) findViewById(R.id.panel_mensajes);
        boton2Radio = (LinearLayout) findViewById(R.id.view_2respuestas);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group_botones);
        radioButton1 = (RadioButton) findViewById(R.id.radio_boton1_text);
        radioButton2 = (RadioButton) findViewById(R.id.radio_boton2_text);
        check = (LinearLayout) findViewById(R.id.view_4respuestas_check);
        boton4 = (LinearLayout) findViewById(R.id.view_4respuestas_boton);
        infoJugador1 = (ImageView) findViewById(R.id.info_jugador1);
        infoJugador2 = (ImageView) findViewById(R.id.info_jugador2);
        infoJugador3 = (ImageView) findViewById(R.id.info_jugador3);
        infoJugador4 = (ImageView) findViewById(R.id.info_jugador4);
        mensaje = (ImageView) findViewById(R.id.mensaje_image_view);
        nombreUsuario1 = (TextView) findViewById(R.id.j1_text);
        nombreUsuario2 = (TextView) findViewById(R.id.j2_text);
        nombreUsuario3 = (TextView) findViewById(R.id.j3_text);
        nombreUsuario4 = (TextView) findViewById(R.id.j4_text);
        puntosUsuario1 = (TextView) findViewById(R.id.puntos_j1_text);
        puntosUsuario2 = (TextView) findViewById(R.id.puntos_j2_text);
        puntosUsuario3 = (TextView) findViewById(R.id.puntos_j3_text);
        puntosUsuario4 = (TextView) findViewById(R.id.puntos_j4_text);
        preguntaText = (TextView) findViewById(R.id.pregunta_text_view);
        botonOneText = (TextView) findViewById(R.id.boton_one_text);
        textBoton1 = (TextView) findViewById(R.id.boton1_text);
        textBoton2 = (TextView) findViewById(R.id.boton2_text);
        textBoton3 = (TextView) findViewById(R.id.boton3_text);
        textBoton4 = (TextView) findViewById(R.id.boton4_text);
        imageButton1 = (ImageButton) findViewById(R.id.respuesta1_image_button);
        imageButton2 = (ImageButton) findViewById(R.id.respuesta2_image_button);
        imageButton3 = (ImageButton) findViewById(R.id.respuesta3_image_button);
        imageButton4 = (ImageButton) findViewById(R.id.respuesta4_image_button);
        check1 = (CheckBox) findViewById(R.id.check1_text);
        check2 = (CheckBox) findViewById(R.id.check2_text);
        check3 = (CheckBox) findViewById(R.id.check3_text);
        check4 = (CheckBox) findViewById(R.id.check4_text);

    }
    private void dameListener(){
        botonOne.setOnClickListener(this);
        botonTwo.setOnClickListener(this);
        botonThree.setOnClickListener(this);
        imageButton1.setOnClickListener(this);
        imageButton2.setOnClickListener(this);
        imageButton3.setOnClickListener(this);
        imageButton4.setOnClickListener(this);
        radioButton1.setOnClickListener(this);
        radioButton2.setOnClickListener(this);
        crono.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if(crono.getText().equals("00:30")){
                    if(boton2Radio.getVisibility() == View.VISIBLE){
                        evaluaRespuestas(boton2Radio.getId());
                    } else if(check.getVisibility() == View.VISIBLE){
                        evaluaRespuestas(check.getId());
                    } else if(boton4.getVisibility() == View.VISIBLE){
                        evaluaRespuestas(boton4.getId());
                    } else {  }
                }
            }
        });
    }
}
