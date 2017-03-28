package com.example.android.drjardin;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.android.drjardin.modelo.Persona;
import com.example.android.drjardin.modelo.ServicioWeb;
import com.example.android.drjardin.modelo.toStringFromString;

import org.ksoap2.serialization.SoapPrimitive;

import java.io.IOException;

public class ScoreSettings extends AppCompatActivity implements View.OnClickListener{

    private RadioButton radio1, radio2, radio3, radio4, radioJugador1Si,radioJugador1No,radioJugador2Si,radioJugador2No,radioJugador3Si,radioJugador3No,radioJugador4Si,radioJugador4No;
    private Button boton1, boton2, boton3, boton4, botonContinuar;
    private RelativeLayout numJugadores,login1,login2,login3,login4;
    private LinearLayout radioLogin1View,radioLogin2View,radioLogin3View,radioLogin4View,user1View,password1View,progress1View,user2View,password2View,progress2View,user3View,password3View,progress3View,user4View,password4View,progress4View;
    private Persona persona;
    private Persona j1, j2, j3, j4;
    private String mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_settings);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        declareFields();
        dameListener();
        damePanel(View.VISIBLE,View.GONE,View.GONE,View.GONE,View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_registro:
                newActivity(RegistroActivity.class);
                return true;
            case R.id.action_configuracion:
                newActivity(ConfiguracionActivity.class);
                return true;
            case R.id.action_historia:
                newActivity(HistoriaActivity.class);
                return true;
            case R.id.action_inicio:
            case R.id.inicio:
                newActivity(ScrollingActivity.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void newActivity(Object object){

        Intent intent = new Intent(this, (Class<?>) object);
        startActivity(intent);

    }

    private void showToast(String mensaje){

        Toast.makeText(this, mensaje,Toast.LENGTH_SHORT).show();

    }

    private void damePanel(int viewNumJugadores,int viewLogin1,int viewLogin2,int viewLogin3,int viewLogin4){
        numJugadores.setVisibility(viewNumJugadores);
        login1.setVisibility(viewLogin1);
        login2.setVisibility(viewLogin2);
        login3.setVisibility(viewLogin3);
        login4.setVisibility(viewLogin4);
    }

    private void damePanelLogin1(int radio,int user,int password,int botonLogin,int progress){
        radioLogin1View.setVisibility(radio);
        user1View.setVisibility(user);
        password1View.setVisibility(password);
        boton1.setVisibility(botonLogin);
        progress1View.setVisibility(progress);
    }

    private void damePanelLogin2(int radio,int user,int password,int botonLogin,int progress){
        radioLogin2View.setVisibility(radio);
        user2View.setVisibility(user);
        password2View.setVisibility(password);
        boton2.setVisibility(botonLogin);
        progress2View.setVisibility(progress);
    }
    private void damePanelLogin3(int radio,int user,int password,int botonLogin,int progress){
        radioLogin3View.setVisibility(radio);
        user3View.setVisibility(user);
        password3View.setVisibility(password);
        boton3.setVisibility(botonLogin);
        progress3View.setVisibility(progress);
    }
    private void damePanelLogin4(int radio,int user,int password,int botonLogin,int progress){
        radioLogin4View.setVisibility(radio);
        user4View.setVisibility(user);
        password4View.setVisibility(password);
        boton4.setVisibility(botonLogin);
        progress4View.setVisibility(progress);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.radio_1_jugador:
                ScrollingActivity.numJugadores = 1;
                damePanelLogin1(View.VISIBLE,View.GONE,View.GONE,View.GONE,View.GONE);
                damePanel(View.GONE,View.VISIBLE,View.GONE,View.GONE,View.GONE);
                break;
            case R.id.radio_2_jugadores:
                ScrollingActivity.numJugadores = 2;
                damePanelLogin1(View.VISIBLE,View.GONE,View.GONE,View.GONE,View.GONE);
                damePanel(View.GONE,View.VISIBLE,View.GONE,View.GONE,View.GONE);
                break;
            case R.id.radio_3_jugadores:
                ScrollingActivity.numJugadores = 3;
                damePanelLogin1(View.VISIBLE,View.GONE,View.GONE,View.GONE,View.GONE);
                damePanel(View.GONE,View.VISIBLE,View.GONE,View.GONE,View.GONE);
                break;
            case R.id.radio_4_jugadores:
                ScrollingActivity.numJugadores = 4;
                damePanelLogin1(View.VISIBLE,View.GONE,View.GONE,View.GONE,View.GONE);
                damePanel(View.GONE,View.VISIBLE,View.GONE,View.GONE,View.GONE);
                break;
            case R.id.jugador1_si_login:
                damePanelLogin1(View.GONE,View.VISIBLE,View.VISIBLE,View.VISIBLE,View.GONE);
                break;
            case R.id.jugador1_no_login:
                damePanelLogin1(View.GONE,View.GONE,View.GONE,View.GONE,View.GONE);
                ScrollingActivity.jugador1 = dameAnonimo();
                if (ScrollingActivity.numJugadores>1){
                    damePanelLogin2(View.VISIBLE,View.GONE,View.GONE,View.GONE,View.GONE);
                    damePanel(View.GONE,View.GONE,View.VISIBLE,View.GONE,View.GONE);
                } else {
                    botonContinuar.setVisibility(View.VISIBLE);
                }


                break;
            case R.id.jugador2_si_login:
                damePanelLogin2(View.GONE,View.VISIBLE,View.VISIBLE,View.VISIBLE,View.GONE);
                break;
            case R.id.jugador2_no_login:
                ScrollingActivity.jugador2 = dameAnonimo();
                if (ScrollingActivity.numJugadores>2){
                    damePanelLogin3(View.VISIBLE,View.GONE,View.GONE,View.GONE,View.GONE);
                    damePanel(View.GONE,View.GONE,View.GONE,View.VISIBLE,View.GONE);
                } else {
                    botonContinuar.setVisibility(View.VISIBLE);
                }
                damePanelLogin2(View.GONE,View.GONE,View.GONE,View.GONE,View.GONE);
                break;
            case R.id.jugador3_si_login:
                damePanelLogin3(View.GONE,View.VISIBLE,View.VISIBLE,View.VISIBLE,View.GONE);
                break;
            case R.id.jugador3_no_login:
                ScrollingActivity.jugador3 = dameAnonimo();
                if (ScrollingActivity.numJugadores>3){
                    damePanelLogin4(View.VISIBLE,View.GONE,View.GONE,View.GONE,View.GONE);
                    damePanel(View.GONE,View.GONE,View.GONE,View.GONE,View.VISIBLE);
                } else {
                    botonContinuar.setVisibility(View.VISIBLE);
                }
                damePanelLogin3(View.GONE,View.GONE,View.GONE,View.GONE,View.GONE);
                break;
            case R.id.jugador4_si_login:
                damePanelLogin4(View.GONE,View.VISIBLE,View.VISIBLE,View.VISIBLE,View.GONE);
                break;
            case R.id.jugador4_no_login:
                botonContinuar.setVisibility(View.VISIBLE);
                ScrollingActivity.jugador4 = dameAnonimo();
                damePanelLogin4(View.GONE,View.GONE,View.GONE,View.GONE,View.GONE);
                break;
            case R.id.login_jugador1:
                if(damePersona(R.id.usuario1,R.id.password1,j1)){
                    damePanelLogin1(View.GONE,View.GONE,View.GONE,View.GONE,View.VISIBLE);
                    Login login = new Login();
                    login.execute();
                } else {
                    showToast("INTENTELO DE NUEVO");
                }
                break;
            case R.id.login_jugador2:
                if(damePersona(R.id.usuario2,R.id.password2,j2)){
                    damePanelLogin2(View.GONE,View.GONE,View.GONE,View.GONE,View.VISIBLE);
                    Login login = new Login();
                    login.execute();
                } else {
                    showToast("INTENTELO DE NUEVO");
                }
                break;
            case R.id.login_jugador3:
                if(damePersona(R.id.usuario3,R.id.password3,j3)){
                    damePanelLogin3(View.GONE,View.GONE,View.GONE,View.GONE,View.VISIBLE);
                    Login login = new Login();
                    login.execute();

                } else {
                    showToast("INTENTELO DE NUEVO");
                }

                break;
            case R.id.login_jugador4:
                if(damePersona(R.id.usuario4,R.id.password4,j4)){
                    damePanelLogin4(View.GONE,View.GONE,View.GONE,View.GONE,View.VISIBLE);
                    Login login = new Login();
                    login.execute();
                } else {
                    showToast("INTENTELO DE NUEVO");
                }
                break;
            case R.id.settings_continuar:
                newActivity(JuegoActivity.class);
                break;
            default:
                break;
        }
    }

    private Boolean damePersona(int usuario,int password,Persona jugador) {
        boolean respuesta = false;
        int correcto = 0;

        EditText editText = (EditText) findViewById(usuario);
        if (TextUtils.isEmpty(editText.getText().toString().trim())) correcto++;
        jugador.setUsuario(editText.getText().toString().trim());

        editText = (EditText) findViewById(password);
        if (TextUtils.isEmpty(editText.getText().toString().trim())) correcto++;
        jugador.setPassword(editText.getText().toString().trim());

        if (correcto > 0) {
            respuesta = false;
            showToast("RELLENE CAMPOS OBLIGATORIOS");
        } else {
            respuesta = true;
            persona = jugador;
        }
        return respuesta;
    }


    private class Login extends AsyncTask<Void, Integer, String> {

        @Override
        protected String doInBackground(Void... params) {
            mensaje = null;
            try {
                mensaje = toStringFromString.toString(persona);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String respuesta;
            ServicioWeb servicioWeb = new ServicioWeb("listarUsuario");
            SoapPrimitive response = servicioWeb.llamadaServicio(mensaje);
            respuesta = response.toString();

            return respuesta;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0].intValue();
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String respuesta) {
            if (progress1View.getVisibility() == View.VISIBLE){
                if(respuesta.equals("RESULTADO INESPERADO")){
                    showToast(respuesta);
                    damePanelLogin1(View.GONE,View.VISIBLE,View.VISIBLE,View.VISIBLE,View.GONE);
                } else {
                    persona = (Persona) toStringFromString.fromString(respuesta);
                    ScrollingActivity.jugador1 = persona;
                    showToast("¡DATOS CORRECTOS!");
                    if(ScrollingActivity.numJugadores==1){
                        progress1View.setVisibility(View.INVISIBLE);
                        botonContinuar.setVisibility(View.VISIBLE);
                    } else {
                        damePanelLogin1(View.GONE,View.GONE,View.GONE,View.GONE,View.GONE);
                        damePanelLogin2(View.VISIBLE,View.GONE,View.GONE,View.GONE,View.GONE);
                        damePanel(View.GONE,View.GONE,View.VISIBLE,View.GONE,View.GONE);
                    }
                }
            } else if(progress2View.getVisibility() == View.VISIBLE){
                if(respuesta.equals("RESULTADO INESPERADO")){
                    showToast(respuesta);
                    damePanelLogin2(View.GONE,View.VISIBLE,View.VISIBLE,View.VISIBLE,View.GONE);
                } else {
                    persona = (Persona) toStringFromString.fromString(respuesta);
                    ScrollingActivity.jugador2 = persona;
                    showToast("¡DATOS CORRECTOS!");
                    if(ScrollingActivity.numJugadores==2){
                        progress2View.setVisibility(View.INVISIBLE);
                        botonContinuar.setVisibility(View.VISIBLE);
                    } else {
                        damePanelLogin2(View.GONE,View.GONE,View.GONE,View.GONE,View.GONE);
                        damePanelLogin3(View.VISIBLE,View.GONE,View.GONE,View.GONE,View.GONE);
                        damePanel(View.GONE,View.GONE,View.GONE,View.VISIBLE,View.GONE);
                    }
                }

            } else if(progress3View.getVisibility() == View.VISIBLE){
                if(respuesta.equals("RESULTADO INESPERADO")){
                    showToast(respuesta);
                    damePanelLogin3(View.GONE,View.VISIBLE,View.VISIBLE,View.VISIBLE,View.GONE);
                } else {
                    persona = (Persona) toStringFromString.fromString(respuesta);
                    ScrollingActivity.jugador3 = persona;
                    showToast("¡DATOS CORRECTOS!");
                    if(ScrollingActivity.numJugadores==3){
                        progress3View.setVisibility(View.INVISIBLE);
                        botonContinuar.setVisibility(View.VISIBLE);
                    } else {
                        damePanelLogin3(View.GONE,View.GONE,View.GONE,View.GONE,View.GONE);
                        damePanelLogin4(View.VISIBLE,View.GONE,View.GONE,View.GONE,View.GONE);
                        damePanel(View.GONE,View.GONE,View.GONE,View.GONE,View.VISIBLE);
                    }
                }

            } else if (progress4View.getVisibility() == View.VISIBLE){
                if(respuesta.equals("RESULTADO INESPERADO")){
                    showToast(respuesta);
                    damePanelLogin4(View.GONE,View.VISIBLE,View.VISIBLE,View.VISIBLE,View.GONE);
                } else {
                    progress4View.setVisibility(View.INVISIBLE);
                    botonContinuar.setVisibility(View.VISIBLE);
                    persona = (Persona) toStringFromString.fromString(respuesta);
                    ScrollingActivity.jugador4 = persona;
                    showToast("¡DATOS CORRECTOS!");
                }
            }
        }
    }

    public Persona dameAnonimo(){
        Persona anonimo = new Persona();
        anonimo.setNombre("ANONIMO");
        anonimo.setUsuario("ANONIMO");
        return anonimo;
    }

    private void dameListener(){
        radio1.setOnClickListener(this);
        radio2.setOnClickListener(this);
        radio3.setOnClickListener(this);
        radio4.setOnClickListener(this);
        boton1.setOnClickListener(this);
        boton2.setOnClickListener(this);
        boton3.setOnClickListener(this);
        boton4.setOnClickListener(this);
        botonContinuar.setOnClickListener(this);
        radioJugador1Si.setOnClickListener(this);
        radioJugador1No.setOnClickListener(this);
        radioJugador2Si.setOnClickListener(this);
        radioJugador2No.setOnClickListener(this);
        radioJugador3Si.setOnClickListener(this);
        radioJugador3No.setOnClickListener(this);
        radioJugador4Si.setOnClickListener(this);
        radioJugador4No.setOnClickListener(this);
    }

    private void declareFields(){
        persona = new Persona();
        j1 = new Persona();
        j2 = new Persona();
        j3 = new Persona();
        j4 = new Persona();
        numJugadores = (RelativeLayout) findViewById(R.id.view_num_jugadores);
        login1 = (RelativeLayout) findViewById(R.id.view_login1);
        login2 = (RelativeLayout) findViewById(R.id.view_login2);
        login3 = (RelativeLayout) findViewById(R.id.view_login3);
        login4 = (RelativeLayout) findViewById(R.id.view_login4);
        radioLogin1View =(LinearLayout) findViewById(R.id.radio_login_1);
        radioLogin2View=(LinearLayout) findViewById(R.id.radio_login_2);
        radioLogin3View=(LinearLayout) findViewById(R.id.radio_login_3);
        radioLogin4View=(LinearLayout) findViewById(R.id.radio_login_4);
        user1View=(LinearLayout) findViewById(R.id.user1_view);
        password1View=(LinearLayout) findViewById(R.id.password1_view);
        progress1View=(LinearLayout) findViewById(R.id.progress1_view);
        user2View=(LinearLayout) findViewById(R.id.user2_view);
        password2View=(LinearLayout) findViewById(R.id.password2_view);
        progress2View=(LinearLayout) findViewById(R.id.progress2_view);
        user3View=(LinearLayout) findViewById(R.id.user3_view);
        password3View=(LinearLayout) findViewById(R.id.password3_view);
        progress3View=(LinearLayout) findViewById(R.id.progress3_view);
        user4View=(LinearLayout) findViewById(R.id.user4_view);
        password4View=(LinearLayout) findViewById(R.id.password4_view);
        progress4View=(LinearLayout) findViewById(R.id.progress4_view);
        radio1 = (RadioButton) findViewById(R.id.radio_1_jugador);
        radio2 = (RadioButton) findViewById(R.id.radio_2_jugadores);
        radio3 = (RadioButton) findViewById(R.id.radio_3_jugadores);
        radio4 = (RadioButton) findViewById(R.id.radio_4_jugadores);
        radioJugador1Si = (RadioButton) findViewById(R.id.jugador1_si_login);
        radioJugador1No = (RadioButton) findViewById(R.id.jugador1_no_login);
        radioJugador2Si = (RadioButton) findViewById(R.id.jugador2_si_login);
        radioJugador2No = (RadioButton) findViewById(R.id.jugador2_no_login);
        radioJugador3Si = (RadioButton) findViewById(R.id.jugador3_si_login);
        radioJugador3No = (RadioButton) findViewById(R.id.jugador3_no_login);
        radioJugador4Si = (RadioButton) findViewById(R.id.jugador4_si_login);
        radioJugador4No = (RadioButton) findViewById(R.id.jugador4_no_login);
        boton1 = (Button) findViewById(R.id.login_jugador1);
        boton2 = (Button) findViewById(R.id.login_jugador2);
        boton3 = (Button) findViewById(R.id.login_jugador3);
        boton4 = (Button) findViewById(R.id.login_jugador4);
        botonContinuar = (Button) findViewById(R.id.settings_continuar);
    }
}
