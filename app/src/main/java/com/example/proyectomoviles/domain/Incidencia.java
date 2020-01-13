package com.example.proyectomoviles.domain;

public class Incidencia {

    private String titulo;
    private String dia;
    private String periodo;
    private String lugar;
    private String riesgo;
    private String descripcion;
    private String foto;

    public Incidencia() {
    }

    public Incidencia(String titulo, String dia, String periodo, String lugar, String riesgo, String descripcion) {
        this.titulo = titulo;
        this.dia = dia;
        this.periodo = periodo;
        this.lugar = lugar;
        this.riesgo = riesgo;
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getRiesgo() {
        return riesgo;
    }

    public void setRiesgo(String riesgo) {
        this.riesgo = riesgo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
