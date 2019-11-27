package com.example.t3_ejer6_peliculas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class ActivityCompleta extends AppCompatActivity {

    ActionBar actionBar;
    RecyclerView rcvCompleta;
    AdaptadorCompleta adaptadorCompleta;
    ArrayList<Pelicula> pelisCompleta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completa);

        actionBar = getSupportActionBar();
        this.actionBar.setTitle("Lista Completa");

        pelisCompleta = (ArrayList<Pelicula>) getIntent().getSerializableExtra("arrPelis");
        rcvCompleta = findViewById(R.id.rcvCompleta);
        adaptadorCompleta = new AdaptadorCompleta(this,pelisCompleta,rcvCompleta);

        LinearLayoutManager llmListaCompleta = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvCompleta.setLayoutManager(llmListaCompleta);

        View.OnClickListener oclCompleta = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = rcvCompleta.getChildAdapterPosition(v);
                Intent itSinopsis = new Intent(ActivityCompleta.this,ActivitySinopsis.class);
                itSinopsis.putExtra("peliculasinopsis",pelisCompleta.get(pos));
                startActivity(itSinopsis);
            }
        };
        adaptadorCompleta.setOclCompleta(oclCompleta);

        rcvCompleta.setAdapter(adaptadorCompleta);
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
}