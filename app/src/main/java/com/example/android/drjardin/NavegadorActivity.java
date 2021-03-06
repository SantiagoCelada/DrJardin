package com.example.android.drjardin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class NavegadorActivity extends AppCompatActivity {

    private WebView web;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegador);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        url = getIntent().getExtras().getString("mensaje");
        web = (WebView) findViewById(R.id.web_view);
        web.loadUrl(url);

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
}
