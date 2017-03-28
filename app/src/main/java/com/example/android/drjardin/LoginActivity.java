package com.example.android.drjardin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.drjardin.modelo.Persona;
import com.example.android.drjardin.modelo.ServicioWeb;
import com.example.android.drjardin.modelo.toStringFromString;

import org.ksoap2.serialization.SoapPrimitive;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private String mensaje = "";
    private String mensajeToast = "";
    Persona persona;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        boton = (Button) findViewById(R.id.boton_login);
        boton.setOnClickListener(this);
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

    private void newActivity(Object object, String mensaje){

        Intent intent = new Intent(this, (Class<?>) object);
        intent.putExtra("mensaje",mensaje);
        startActivity(intent);

    }

    private void showToast(String mensaje){

        Toast.makeText(this, mensaje,Toast.LENGTH_SHORT).show();

    }

    private Boolean damePersona() {
        persona = new Persona();
        boolean respuesta = false;
        int correcto = 0;

        EditText editText = (EditText) findViewById(R.id.usuario);
        if (TextUtils.isEmpty(editText.getText().toString().trim())) correcto++;
        persona.setUsuario(editText.getText().toString().trim());
        editText = (EditText) findViewById(R.id.password);
        if (TextUtils.isEmpty(editText.getText().toString().trim())) correcto++;
        persona.setPassword(editText.getText().toString().trim());

        if (correcto > 0) {
            respuesta = false;
            mensajeToast = "RELLENE CAMPOS OBLIGATORIOS";
        } else {
            respuesta = true;
        }
        return respuesta;
    }

    @Override
    public void onClick(View v) {

        if(damePersona()){
            try {
                mensaje = toStringFromString.toString(persona);
            } catch (Exception e) {
                e.printStackTrace();
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ServicioWeb servicioWeb = new ServicioWeb("listarUsuario");
                    SoapPrimitive response = servicioWeb.llamadaServicio(mensaje);
                    String respuesta = response.toString();
                    if(respuesta.equals("RESULTADO INESPERADO")){
                        showToast(respuesta);
                    } else {
                        ScrollingActivity.login = true;
                        ScrollingActivity.user = (Persona) toStringFromString.fromString(response.toString());
                        newActivity(MensajeActivity.class,"¡DATOS CORRECTOS!");
                    }
                }
            }).start();
        } else {
            showToast(mensajeToast);
        }
    }
}
