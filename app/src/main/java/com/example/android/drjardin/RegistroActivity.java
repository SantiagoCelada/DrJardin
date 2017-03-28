package com.example.android.drjardin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        dameListener();
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

    private void newActivity(Object object){

        Intent intent = new Intent(this, (Class<?>) object);
        startActivity(intent);

    }

    private void dameListener(){
        int[] imagenes = new int[]{R.id.agregar_image,R.id.login_image,R.id.eliminar_image,R.id.actualizar_image};
        for(int imagenId: imagenes){
            ImageView imagen = (ImageView) findViewById(imagenId);
            imagen.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.agregar_image:
                if(ScrollingActivity.login){
                    Toast.makeText(this, "YA ESTÁ REGISTRADO",Toast.LENGTH_LONG).show();
                }else {
                    newActivity(AgregarActivity.class);
                }
                break;
            case R.id.login_image:
                if(ScrollingActivity.login){
                    Toast.makeText(this, "YA HA INICIADO SESIÓN",Toast.LENGTH_LONG).show();
                }else {
                    newActivity(LoginActivity.class);
                }
                break;
            case R.id.eliminar_image:
                if(ScrollingActivity.login){
                    newActivity(EliminarActivity.class);
                }else {
                    Toast.makeText(this, "INICIE SESIÓN",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.actualizar_image:
                if(ScrollingActivity.login){
                    newActivity(ActualizarActivity.class);
                }else {
                    Toast.makeText(this, "INICIE SESIÓN",Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }

    }
}
