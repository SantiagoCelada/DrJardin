package com.example.android.drjardin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MenuScoreActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView image1;
    ImageView image2;
    ImageView image3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_score);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        image1 = (ImageView) findViewById(R.id.play_image);
        image1.setOnClickListener(this);
        image2 = (ImageView) findViewById(R.id.highscore_image);
        image2.setOnClickListener(this);
        image3 = (ImageView) findViewById(R.id.configuracion_image);
        image3.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.play_image:
                newActivity(JuegoActivity.class);
                break;
            case R.id.highscore_image:
                newActivity(HighScores.class);
                break;
            case R.id.configuracion_image:
                newActivity(ScoreSettings.class);
                break;
            default:
                break;
        }

    }
}
