package com.example.t3_ejer6_peliculas;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class ActivityNuevaPeli extends AppCompatActivity {

    ActionBar actBarNueva;
    EditText edtTitulo;
    EditText edtDirector;
    EditText edtDuracion;
    Spinner spnSalas;
    RadioGroup rgPegi;
    DatePicker dtPicker;

    boolean spnShow;
    Pelicula p;

    //---------------------------
    //Contenedores Datos Peli p
    String titulo, director, sinopsis, sala, idYoutube;
    int clasi, portada, duracion;
    Date fecha;
    boolean favorita;
    //---------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_peli);
        setTheme(R.style.AppThemeNewPeli);

        this.actBarNueva = getSupportActionBar();
        this.actBarNueva.setTitle("Nueva película");

        this.spnShow = false;

        //Título
        this.edtTitulo = findViewById(R.id.edtAddTitulo);

        //Director
        this.edtDirector = findViewById(R.id.edtAddDirector);

        //Duración (Si introduce algo que no sea un número positivo, pondrá 0)
        this.edtDuracion = findViewById(R.id.edtAddDuracion);

        //Sala
        String[] arrSalas = getResources().getStringArray(R.array.salasspinner);
        spnSalas = this.findViewById(R.id.spnSalas);

        ArrayAdapter<String> spnArrAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrSalas);
        spnSalas.setAdapter(spnArrAdapter);
        spnSalas.setSelection(0);
//        spnSalas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                sala = parent.getItemAtPosition(position).toString();
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                sala = "";
//            }
//        });

        //Pegi
        this.clasi = R.id.rbtnPegiG; //Default option
        this.rgPegi = findViewById(R.id.rbtnAddGroup);
        this.rgPegi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.rbtnPegiG:
                        clasi = R.drawable.g;
                        break;
                    case R.id.rbtnPegiPG:
                        clasi = R.drawable.pg;
                        break;
                    case R.id.rbtnPegiR:
                        clasi = R.drawable.r;
                        break;
                    case R.id.rbtnPegiPg13:
                        clasi = R.drawable.pg13;
                        break;
                    case R.id.rbtnPegiNc17:
                        clasi = R.drawable.nc17;
                        break;
                }
            }
        });

        //Fecha Estreno
        this.dtPicker = findViewById(R.id.datepicker);
        //por defecto usará la fecha actual
        this.fecha = Calendar.getInstance().getTime();

        //IDYoutube
        this.idYoutube = "dQw4w9WgXcQ";

        //Favorito
        this.favorita = false;

        //Sinopsis
        this.sinopsis = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer ex eros, rhoncus sit amet ligula vitae, dictum pulvinar metus. Integer et risus ligula. Proin nisi nibh, egestas eget diam nec, tincidunt imperdiet erat. Donec nec felis eros. Fusce tincidunt nibh eu ex vulputate eleifend. Donec posuere dolor dui, et porttitor odio vehicula nec. Integer non ultricies orci. Curabitur fermentum mollis libero ac elementum. ";


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menunuevapeli,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.mnoptionNPAdd:
                //Titulo
                this.titulo = this.edtTitulo.getText().toString();

                //Director
                this.director = this.edtDirector.getText().toString();

                //Duración
                try{
                    int aux = Integer.valueOf(this.edtDuracion.getText().toString());
                    if (aux < 0){
                        throw new IllegalArgumentException();
                    }
                    this.duracion = aux;
                }catch (Exception e){
                    this.duracion=0;
                }

                //sala
                this.sala = spnSalas.getSelectedItem().toString();

                //Pegi on Create

                //Fecha
                this.dtPicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar calendario = Calendar.getInstance();
                        calendario.set(year,monthOfYear,dayOfMonth);
                        fecha = calendario.getTime();
                    }
                });
//                if(
//                        this.titulo != "" &&
//                        this.director!= "" &&
//                        this.duracion!= null &&
//                        this.fecha!= null
//                ){

                //Asigna a Peli
                this.p = new Pelicula(
                        this.titulo,
                        this.director,
                        this.duracion,
                        this.fecha,
                        this.sala,
                        this.clasi,
                        R.drawable.sincara
                );
                this.p.setIdYoutube(this.idYoutube);
                this.p.setFavorita(this.favorita);
                this.p.setSinopsis(this.sinopsis);

                Intent itPeliToAdd = new Intent(ActivityNuevaPeli.this,MainActivity.class);
                itPeliToAdd.putExtra("pelicula",this.p);
                this.setResult(RESULT_OK, itPeliToAdd);
                this.finish();
//}
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}