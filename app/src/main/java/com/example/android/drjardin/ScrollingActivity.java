package com.example.android.drjardin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.drjardin.modelo.Medidas;
import com.example.android.drjardin.modelo.Persona;

public class ScrollingActivity extends AppCompatActivity implements View.OnClickListener{

    private String[] addresses;
    private String subject;
    private String text;
    public static boolean login = false;
    public static Persona user = null;
    public static Persona jugador1 = null;
    public static Persona jugador2 = null;
    public static Persona jugador3 = null;
    public static Persona jugador4 = null;
    public static int numJugadores = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        Medidas medidas = new Medidas(this.getApplication());
        medidas.dameMedidasRedesSociales((RelativeLayout)findViewById(R.id.facebook_view),(RelativeLayout)findViewById(R.id.gplus_view),(RelativeLayout)findViewById(R.id.twiter_view),(RelativeLayout)findViewById(R.id.youtube_view));
        medidas.dameMedidasMenuInicio((RelativeLayout)findViewById(R.id.maps_image_view),(RelativeLayout)findViewById(R.id.bandera_maps_view),(RelativeLayout)findViewById(R.id.whatsup_image_view),(RelativeLayout)findViewById(R.id.bandera_whatsup_view),(RelativeLayout)findViewById(R.id.internet_image_view),(RelativeLayout)findViewById(R.id.bandera_internet_view));

        dameListener();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addresses = new String[]{"santiagocelada@yahoo.es"};
                subject = "Contacto Dr Jardín";
                text = "";

                composeEmail(addresses,subject,text);
            }
        });
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

    public void dameListener(){
        int[] imagenes = new int[]{R.id.maps_image, R.id.bandera_maps, R.id.whatsup_image, R.id.bandera_whatsup,
                R.id.internet_image, R.id.bandera_internet, R.id.tienda_image, R.id.consultas_image, R.id.tutoriales_image,
                R.id.compartir_image, R.id.juegos_image, R.id.registro_image, R.id.facebook_image_view, R.id.gplus_image_view,
                R.id.twiter_image_view, R.id.youtube_image_view, R.id.tienda_icon, R.id.consultas_icon, R.id.juegos_icon, R.id.tutoriales_icon};
        int[] textos = new int[]{R.id.titulo_tienda, R.id.titulo_consulta, R.id.titulo_tutoriales, R.id.titulo_compartir, R.id.titulo_juegos, R.id.titulo_registro};
        for(int imagenId: imagenes){
            ImageView imagen = (ImageView) findViewById(imagenId);
            imagen.setOnClickListener(this);
        }
        for(int textoId: textos){
            TextView texto = (TextView) findViewById(textoId);
            texto.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        String uri;
        switch (v.getId()){
            case (R.id.bandera_internet): case (R.id.internet_image):
                newActivity(NavegadorActivity.class,"https://www.google.es");
                break;
            case(R.id.maps_image): case(R.id.bandera_maps):
                uri = "geo:38.010707, -1.164166";
                showMap(Uri.parse(uri));
                break;
            case(R.id.whatsup_image): case(R.id.bandera_whatsup):
                dialPhoneNumber("0034123456789");
                break;
            case(R.id.tienda_image): case(R.id.titulo_tienda): case(R.id.tienda_icon):
                newActivity(TiendaActivity.class);
                break;
            case(R.id.consultas_image): case(R.id.titulo_consulta): case(R.id.consultas_icon):
                newActivity(ConsultaActivity.class);
                break;
            case(R.id.tutoriales_image): case(R.id.titulo_tutoriales): case(R.id.tutoriales_icon):
                newActivity(TutorialesActivity.class);
                break;
            case(R.id.compartir_image): case(R.id.titulo_compartir):
                addresses = new String[]{"yourfriend@gmail.com"};
                subject = "Nueva App Dr. Jardín";
                text = "Hola amigo.\nQuiero que veas la nueva App del Dr. Jardín.\nPuedes descargarla en http\\\\:www.drjardin.es\nEspero que te guste.";
                composeEmail(addresses,subject,text);
                break;
            case(R.id.juegos_image): case(R.id.titulo_juegos): case(R.id.juegos_icon):
                newActivity(ScoreActivity.class);
                break;
            case(R.id.registro_image): case(R.id.titulo_registro):
                newActivity(RegistroActivity.class);
                break;
            case(R.id.facebook_image_view):
                newActivity(NavegadorActivity.class,"https://www.facebook.com");
                break;
            case(R.id.twiter_image_view):
                newActivity(NavegadorActivity.class,"https://www.twitter.com");
                break;
            case(R.id.gplus_image_view):
                newActivity(NavegadorActivity.class,"https://plus.google.com");
                break;
            case(R.id.youtube_image_view):
                newActivity(NavegadorActivity.class,"https://www.youtube.com");
                break;
            default:
                break;
        }
    }

    public void newActivity(Object object){

        Intent intent = new Intent(this, (Class<?>) object);
        startActivity(intent);

    }

    public void newActivity(Object object,String mensaje){

        Intent intent = new Intent(this, (Class<?>) object);
        intent.putExtra("mensaje",mensaje);
        startActivity(intent);

    }

    public void composeEmail(String[] addresses, String subject,String text) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT,text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    public void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
