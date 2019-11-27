package com.example.t3_ejer6_peliculas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdaptadorCompleta
        extends RecyclerView.Adapter<AdaptadorCompleta.MyHolderCompleta>
        implements View.OnClickListener {

    public View.OnClickListener oclCompleta;
    public ArrayList<Pelicula> peliculasCompleta;
    public Context context;
    public RecyclerView recyclerView;
    public int posicionElementoPulsado;

    public AdaptadorCompleta(Context context, ArrayList<Pelicula> peliculasCompleta, RecyclerView recyclerView) {
        this.context = context;
        this.peliculasCompleta = peliculasCompleta;
        this.recyclerView = recyclerView;
        this.posicionElementoPulsado = -1;
    }

    public void setOclCompleta(View.OnClickListener oclCompleta) {
        this.oclCompleta = oclCompleta;
    }

    public int getPosicionElementoPulsado() {
        return this.posicionElementoPulsado;
    }

    @Override
    public void onClick(View v) {
        if(oclCompleta!=null){
            oclCompleta.onClick(v);
        }
    }

    @NonNull
    @Override
    public MyHolderCompleta onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View loQueSeVisualiza =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.celda_completa, parent,false);
        loQueSeVisualiza.setOnClickListener(this);
        return new MyHolderCompleta(loQueSeVisualiza);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolderCompleta holder, int position) {
        Pelicula p = peliculasCompleta.get(position);
        holder.txvTituloCompleta.setText(p.getTitulo());
        holder.txvDirectorCompleta.setText(p.getDirector());
        holder.txvDuracionCompleta.setText(p.getDuracion()+" min.");
        holder.txvFechaCompleta.setText(this.getFechaFromDate(p.getFecha()));
        holder.txvSalaCompleta.setText(p.getSala());
        holder.imgvPortadaCompleta.setImageResource(p.getPortada());
        holder.imgvPegiCompleta.setImageResource(p.getClasi());
        if(p.getFavorita()){
            holder.imgvFavCompleta.setImageResource(R.drawable.robot_check);
        }else{
           holder.imgvFavCompleta.setImageResource(R.drawable.robot);
        }
    }

    @Override
    public int getItemCount() {
        return this.peliculasCompleta.size();
    }

    public class MyHolderCompleta extends RecyclerView.ViewHolder{

        TextView txvTituloCompleta;
        TextView txvDirectorCompleta;
        TextView txvFechaCompleta;
        TextView txvDuracionCompleta;
        TextView txvSalaCompleta;
        ImageView imgvPortadaCompleta;
        ImageView imgvFavCompleta;
        ImageView imgvPegiCompleta;

        public MyHolderCompleta(@NonNull View itemView) {
            super(itemView);
            txvTituloCompleta = itemView.findViewById(R.id.txvComTitulo);
            txvDirectorCompleta = itemView.findViewById(R.id.txvComDirector);
            txvFechaCompleta = itemView.findViewById(R.id.txvComFEstreno);
            txvDuracionCompleta = itemView.findViewById(R.id.txvComDuracion);
            txvSalaCompleta = itemView.findViewById(R.id.txvComSala);
            imgvPortadaCompleta = itemView.findViewById(R.id.imgvComPortada);
            imgvFavCompleta = itemView.findViewById(R.id.imgvComFav);
            imgvPegiCompleta = itemView.findViewById(R.id.imgvComPegi);
        }
    }

    public String getFechaFromDate(Date date){
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            date = dateformat.parse(date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.toString();
    }


}