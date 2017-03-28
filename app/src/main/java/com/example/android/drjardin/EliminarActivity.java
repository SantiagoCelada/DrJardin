package com.example.android.drjardin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.android.drjardin.modelo.Persona;
import com.example.android.drjardin.modelo.ServicioWeb;
import com.example.android.drjardin.modelo.toStringFromString;

import org.ksoap2.serialization.SoapPrimitive;

import java.io.IOException;

public class EliminarActivity extends AppCompatActivity implements View.OnClickListener{

    private String mensaje = "";
    private Persona persona;
    private Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);
        boton = (Button) findViewById(R.id.boton_confirmar_eliminar);
        boton.setOnClickListener(this);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    private void newActivity(Object object) {

        Intent intent = new Intent(this, (Class<?>) object);
        startActivity(intent);

    }

    private void newActivity(Object object, String mensaje) {

        Intent intent = new Intent(this, (Class<?>) object);
        intent.putExtra("mensaje", mensaje);
        startActivity(intent);

    }

    private void showToast(String mensaje){

        Toast.makeText(this, mensaje,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {

        RadioButton radioSi = (RadioButton) findViewById(R.id.radio_si_eliminar);
        RadioButton radioNo = (RadioButton) findViewById(R.id.radio_no_eliminar);
        if(radioSi.isChecked()) {
            persona = ScrollingActivity.user;
            try {
                mensaje = toStringFromString.toString(persona);
            } catch (IOException e) {
                e.printStackTrace();
            }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ServicioWeb servicioWeb = new ServicioWeb("eliminarUsuario");
                        SoapPrimitive response = servicioWeb.llamadaServicio(mensaje);
                        String respuesta = response.toString();
                        if(respuesta.equals("RESULTADO INESPERADO")){
                            showToast("response");
                        } else {
                            ScrollingActivity.login = false;
                            ScrollingActivity.user = null;
                            newActivity(MensajeActivity.class,"USUARIO ELIMINADO");
                        }
                    }
                }).start();
        } else if(radioNo.isChecked()) {
            newActivity(RegistroActivity.class);
        } else {
            showToast("");
        }
    }
}
