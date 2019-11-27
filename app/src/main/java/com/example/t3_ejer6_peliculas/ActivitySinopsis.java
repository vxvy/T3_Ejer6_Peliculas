package com.example.t3_ejer6_peliculas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivitySinopsis extends AppCompatActivity {

    ActionBar actionBar;
    Pelicula pSinopsis;
    TextView txvSinopsis;
    ImageView imgvPortada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinopsis);

        Intent it = getIntent();
        pSinopsis=(Pelicula)(it.getSerializableExtra("peliculasinopsis"));

        this.txvSinopsis = findViewById(R.id.txvSinopsis);
        this.txvSinopsis.setText(pSinopsis.getSinopsis());

        this.imgvPortada = findViewById(R.id.imgvSinopsisPortada);
        this.imgvPortada.setImageResource(pSinopsis.getPortada());
        this.imgvPortada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivitySinopsis.this.verTrailer(pSinopsis.getIdYoutube());
            }
        });
        actionBar = getSupportActionBar();
        this.actionBar.setTitle(pSinopsis.getTitulo());
    }

    public void verTrailer(String id){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            startActivity(webIntent);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menucompleta,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnoptionComBack:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
