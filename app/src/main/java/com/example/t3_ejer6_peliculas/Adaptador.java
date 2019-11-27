package com.example.t3_ejer6_peliculas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptador
        extends RecyclerView.Adapter<Adaptador.MyHolder>
        implements View.OnClickListener{

    ArrayList<Pelicula> peliculas;
    View.OnClickListener ocl;

    //Constructor
    public Adaptador(ArrayList<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    //OnClickSetter
    public void setOcl(View.OnClickListener ocl) {
        this.ocl = ocl;
    }

    //Adapter
    @NonNull
    @Override
    public Adaptador.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View loQueSeVaAVer =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.celda, parent, false);
        loQueSeVaAVer.setOnClickListener(this);
        MyHolder contenidoDeCelda = new MyHolder(loQueSeVaAVer);
        return contenidoDeCelda;
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador.MyHolder holder, int position) {
        Pelicula pelicula = peliculas.get(position);
        holder.txvTitulo.setText(pelicula.getTitulo());
        holder.txvDire.setText(pelicula.getDirector());
        holder.imgvPortada.setImageResource(pelicula.portada);
        holder.imgvPegi.setImageResource(pelicula.clasi);
    }

    @Override
    public int getItemCount() {
        return this.peliculas.size();
    }

    //OnClick
    @Override
    public void onClick(View v) {
        if(ocl!=null){
            ocl.onClick(v);
        }

    }

    public static class MyHolder extends RecyclerView.ViewHolder{

        TextView txvTitulo, txvDire;
        ImageView imgvPortada, imgvPegi;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            this.txvTitulo = itemView.findViewById(R.id.txvTitle);
            this.txvDire = itemView.findViewById(R.id.txvDirector);
            this.imgvPortada = itemView.findViewById(R.id.imgvPoster);
            this.imgvPegi = itemView.findViewById(R.id.imgvPegi);
        }
    }

}
