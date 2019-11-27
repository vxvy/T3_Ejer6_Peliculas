package com.example.t3_ejer6_peliculas;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

    public class Pelicula implements Serializable{
        String titulo, director, sinopsis, sala, idYoutube;
        int clasi, portada, duracion;
        Date fecha;
        boolean favorita;

        //private SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");

        public Pelicula(String titulo, String director, int duracion, Date fecha, String sala, int clasi, int portada) {
            this.clasi=clasi;
            this.director=director;
            this.fecha=fecha;
            this.portada=portada;
            this.sala=sala;
            this.titulo=titulo;
            this.duracion=duracion;
            this.favorita=false;
        }

        public String getIdYoutube() {
            return idYoutube;
        }
        public void setIdYoutube(String idYoutube) {
            this.idYoutube=idYoutube;
        }
        public int getClasi() {
            return clasi;
        }
        public void setClasi(int clasi) {
            this.clasi=clasi;
        }
        public String getDirector() {
            return director;
        }
        public void setDirector(String director) {
            this.director=director;
        }
        public boolean getFavorita() {
            return favorita;
        }
        public void setFavorita(Boolean favorita) {
            this.favorita=favorita;
        }
        public Date getFecha() {
//            Date dt = new Date();
//            try {
//                dt = dateformat.parse(this.fecha.toString());
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            return dt.toString();
            return this.fecha;
        }
        public void setFecha(Date fecha) {
            this.fecha=fecha;
        }
        public int getPortada() {
            return portada;
        }
        public void setPortada(int portada) {
            this.portada=portada;
        }
        public String getSala() {
            return sala;
        }
        public void setSala(String sala) {
            this.sala=sala;
        }
        public String getSinopsis() {
            return sinopsis;
        }
        public void setSinopsis(String sinopsis) {
            this.sinopsis=sinopsis;
        }
        public String getTitulo() {
            return titulo;
        }
        public void setTitulo(String titulo) {
            this.titulo=titulo;
        }

        public int getDuracion() {
            return duracion;
        }
        public void setDuracion(int duracion) {
            this.duracion=duracion;
        }


}
