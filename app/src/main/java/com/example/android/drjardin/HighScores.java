package com.example.android.drjardin;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.drjardin.modelo.Partida;
import com.example.android.drjardin.modelo.RecordPartidas;
import com.example.android.drjardin.modelo.ServicioWeb;
import com.example.android.drjardin.modelo.toStringFromString;

import org.ksoap2.serialization.SoapPrimitive;

public class HighScores extends AppCompatActivity {

    private RecordPartidas records;
    private ProgressBar pbarProgreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pbarProgreso = (ProgressBar) findViewById(R.id.progress_bar);
        records = new RecordPartidas();
        DameRecords dameRecords = new DameRecords();
        dameRecords.execute();
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


    private void setRecords(){
        int[] idUsusario = {R.id.usuario_record_1,R.id.usuario_record_2,R.id.usuario_record_3,R.id.usuario_record_4,R.id.usuario_record_5,R.id.usuario_record_6,R.id.usuario_record_7,R.id.usuario_record_8,R.id.usuario_record_9,R.id.usuario_record_10};
        int[] idPuntos = {R.id.puntos_record_1,R.id.puntos_record_2,R.id.puntos_record_3,R.id.puntos_record_4,R.id.puntos_record_5,R.id.puntos_record_6,R.id.puntos_record_7,R.id.puntos_record_8,R.id.puntos_record_9,R.id.puntos_record_10};
        TextView usuario;
        TextView punto;
        Partida[] partidas;
        partidas = records.getPartidas();
        Partida partida;
        String[] usuarios;
        int[] puntos;
        for (int n=0;n<10;n++){
            partida = partidas[n];
            usuarios = partida.getNombreUsuario();
            puntos = partida.getPuntos();
            usuario = (TextView) findViewById(idUsusario[n]);
            punto = (TextView) findViewById(idPuntos[n]);
            usuario.setText(usuarios[0]);
            punto.setText(String.valueOf(puntos[0]));
        }
    }


    private class DameRecords extends AsyncTask<Void, Integer, String> {

        @Override
        protected String doInBackground(Void... params) {

            String respuesta = null;
            ServicioWeb servicioWeb = new ServicioWeb("recordPartidas");
            SoapPrimitive response = servicioWeb.llamadaServicio();
            respuesta = response.toString();
            publishProgress(100);

            return respuesta;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0].intValue();

            pbarProgreso.setProgress(progreso);
        }

        @Override
        protected void onPreExecute() {
            pbarProgreso.setMax(100);
            pbarProgreso.setProgress(0);
        }

        @Override
        protected void onPostExecute(String respuesta) {
            pbarProgreso.setVisibility(View.GONE);
            if (respuesta.equals("RESULTADO INESPERADO")){
                showToast(respuesta);
            } else {
                records = (RecordPartidas) toStringFromString.fromString(respuesta);
                setRecords();
            }
        }

    }

}
