package com.example.android.drjardin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class ScoreActivity extends AppCompatActivity implements View.OnClickListener {
    private VideoView video1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        video1 = (VideoView) findViewById(R.id.udacity_video);
        Button boton = (Button) findViewById(R.id.start_boton);
        boton.setOnClickListener(this);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        startVideoView();

    }

    public void startVideoView(){
        Uri path = Uri.parse("android.resource://com.example.android.drjardin/"
                + R.raw.udacity);
        video1.setVideoURI(path);
        video1.start();
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

    public void newActivity(Object object){

        Intent intent = new Intent(this, (Class<?>) object);
        startActivity(intent);

    }

    @Override
    public void onClick(View v) {
        video1.stopPlayback();
        newActivity(MenuScoreActivity.class);

    }
}
