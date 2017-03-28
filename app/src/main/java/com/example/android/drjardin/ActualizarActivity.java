package com.example.android.drjardin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.drjardin.modelo.Persona;
import com.example.android.drjardin.modelo.ServicioWeb;
import com.example.android.drjardin.modelo.toStringFromString;

import org.ksoap2.serialization.SoapPrimitive;

public class ActualizarActivity extends AppCompatActivity implements View.OnClickListener {

    private String mensaje = "";
    private String mensajeToast = "RESULTADO INESPERADO";
    Persona persona;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        boton = (Button) findViewById(R.id.submit);
        boton.setOnClickListener(this);
        setPersona();
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

    private void setPersona(){

        persona = ScrollingActivity.user;

        EditText editText = (EditText) findViewById(R.id.nombre);
        editText.setText(persona.getNombre());

        editText = (EditText) findViewById(R.id.apellido1);
        editText.setText(persona.getPrimerApellido());

        editText = (EditText) findViewById(R.id.apellido2);
        editText.setText(persona.getSegundoApellido());

        editText = (EditText) findViewById(R.id.usuario);
        editText.setText(persona.getUsuario());
        editText.setVisibility(View.GONE);
        TextView textView = (TextView) findViewById(R.id.texto_usuario);
        textView.setVisibility(View.GONE);

        editText = (EditText) findViewById(R.id.password);
        editText.setHint("En blanco password no cambia");

        editText = (EditText) findViewById(R.id.calle);
        editText.setText(persona.getCalle());

        editText = (EditText) findViewById(R.id.numero);
        editText.setText(String.valueOf(persona.getNumero()));

        editText = (EditText) findViewById(R.id.escalera);
        editText.setText(persona.getEscalera());

        editText = (EditText) findViewById(R.id.piso);
        editText.setText(String.valueOf(persona.getPiso()));

        editText = (EditText) findViewById(R.id.puerta);
        editText.setText(persona.getPuerta());

        editText = (EditText) findViewById(R.id.localidad);
        editText.setText(persona.getLocalidad());

        editText = (EditText) findViewById(R.id.pais);
        editText.setText(persona.getPais());

        editText = (EditText) findViewById(R.id.codigoPostal);
        editText.setText(String.valueOf(persona.getCodigoPostal()));

        editText = (EditText) findViewById(R.id.telefono);
        editText.setText(String.valueOf(persona.getTelefono()));

        editText = (EditText) findViewById(R.id.email);
        editText.setText(persona.getCorreo());

    }
    private Boolean damePersona() {

        boolean respuesta = false;
        int correcto = 0;

        EditText editText = (EditText) findViewById(R.id.nombre);
        if (TextUtils.isEmpty(editText.getText().toString().trim())) correcto++;
        persona.setNombre(editText.getText().toString());

        editText = (EditText) findViewById(R.id.apellido1);
        if (TextUtils.isEmpty(editText.getText().toString().trim())) {
            persona.setPrimerApellido("Desconocido");
        } else {
            persona.setPrimerApellido(editText.getText().toString());
        }

        editText = (EditText) findViewById(R.id.apellido2);
        if (TextUtils.isEmpty(editText.getText().toString().trim())) {
            persona.setSegundoApellido("Desconocido");
        } else {
            persona.setSegundoApellido(editText.getText().toString());
        }

        editText = (EditText) findViewById(R.id.usuario);
        if (TextUtils.isEmpty(editText.getText().toString().trim())) correcto++;
        persona.setUsuario(editText.getText().toString());

        editText = (EditText) findViewById(R.id.password);
        if (!TextUtils.isEmpty(editText.getText().toString().trim()))persona.setPassword(editText.getText().toString());

        editText = (EditText) findViewById(R.id.calle);
        if (TextUtils.isEmpty(editText.getText().toString().trim())) {
            persona.setCalle("Desconocido");
        } else {
            persona.setCalle(editText.getText().toString());
        }

        editText = (EditText) findViewById(R.id.numero);
        if (TextUtils.isEmpty(editText.getText().toString().trim())) editText.setText("0");
        persona.setNumero(Integer.parseInt(editText.getText().toString()));

        editText = (EditText) findViewById(R.id.escalera);
        if (TextUtils.isEmpty(editText.getText().toString().trim())) {
            persona.setEscalera("No");
        } else {
            persona.setEscalera(editText.getText().toString());
        }

        editText = (EditText) findViewById(R.id.piso);
        if (TextUtils.isEmpty(editText.getText().toString().trim())) editText.setText("0");
        persona.setPiso(Integer.parseInt(editText.getText().toString()));

        editText = (EditText) findViewById(R.id.puerta);
        if (TextUtils.isEmpty(editText.getText().toString().trim())) {
            persona.setPuerta("No");
        } else {
            persona.setPuerta(editText.getText().toString());
        }

        editText = (EditText) findViewById(R.id.localidad);
        if (TextUtils.isEmpty(editText.getText().toString().trim())) {
            persona.setLocalidad("Desconocido");
        } else {
            persona.setLocalidad(editText.getText().toString());
        }

        editText = (EditText) findViewById(R.id.pais);
        if (TextUtils.isEmpty(editText.getText().toString().trim())) {
            persona.setPais("Desconocido");
        } else {
            persona.setPais(editText.getText().toString());
        }

        editText = (EditText) findViewById(R.id.codigoPostal);
        if (TextUtils.isEmpty(editText.getText().toString().trim())) editText.setText("0");
        persona.setCodigoPostal(Integer.parseInt(editText.getText().toString()));

        editText = (EditText) findViewById(R.id.telefono);
        if (TextUtils.isEmpty(editText.getText().toString().trim())) editText.setText("0");
        persona.setTelefono(Integer.parseInt(editText.getText().toString()));

        editText = (EditText) findViewById(R.id.email);
        if (TextUtils.isEmpty(editText.getText().toString().trim())) correcto++;
        persona.setCorreo(editText.getText().toString());

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

        if (damePersona()) {
            try {
                mensaje = toStringFromString.toString(persona);
            } catch (Exception e) {
                e.printStackTrace();
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ServicioWeb servicioWeb = new ServicioWeb("actualizarUsuario");
                    SoapPrimitive response = servicioWeb.llamadaServicio(mensaje);
                    String respuesta = response.toString();
                    Log.i("ServicioWeb", "respuesta" + respuesta);
                    if (respuesta.equals("USUARIO ACTUALIZADO")) {
                        ScrollingActivity.user = persona;
                        ScrollingActivity.login = true;
                        newActivity(MensajeActivity.class, respuesta);
                    } else {
                        showToast("RESULTADO INESPERADO");
                    }
                }
            }).start();
        } else {
            showToast(mensajeToast);
        }
    }
}
