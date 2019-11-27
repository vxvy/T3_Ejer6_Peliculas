package com.example.t3_ejer6_peliculas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ActivityFavoritos extends AppCompatActivity {

    ActionBar actionBar;
    ListView lvFavoritos;
    ArrayAdapter<String> arrAdapterFavs;
    ArrayList<Pelicula> dupliPelis;
    ArrayList<String> titStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        //añado cosas
        this.actionBar = getSupportActionBar();
        this.actionBar.setTitle("Películas Favoritas");

        //-----------------------
        //Empiezan las cosas del listview

        dupliPelis = (ArrayList<Pelicula>)getIntent().getSerializableExtra("arrPelis");
        titStrings = new ArrayList<String>();

        //Creo un arrayList con los títulos
        for(int i = 0; i < dupliPelis.size(); i++){
            titStrings.add(dupliPelis.get(i).getTitulo());
        }

        //Con esto se visualiza
        lvFavoritos = findViewById(R.id.listView);

        //Seleccionar varios
        lvFavoritos.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        //Creamos el adaptador
        arrAdapterFavs = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, titStrings);

        //Asignamos el adaptador
        lvFavoritos.setAdapter(arrAdapterFavs);

        //Determinamos qué se visualizará en cada celda del adaptador
        for(int i = 0; i< lvFavoritos.getCount(); i++){
            if(dupliPelis.get(i).getFavorita()){
                lvFavoritos.setItemChecked(i,true);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menufavs, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnoptionFavConfirm:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        ListView lvFavs = findViewById(R.id.listView);
        for(int i = 0; i < lvFavs.getCount(); i++){
            boolean auxFav = lvFavs.isItemChecked(i);
            this.dupliPelis.get(i).setFavorita(auxFav);
        }
//        Intent it = new Intent(ActivityFavoritos.this,MainActivity.class);
        Intent it = new Intent();
        it.putExtra("pelisFav",this.dupliPelis);
        this.setResult(RESULT_OK, it);
        finish();
    }
}
