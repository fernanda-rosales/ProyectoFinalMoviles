package com.example.proyectomoviles.domain;

public class Usuario {

    public String nombre;
    public String apellidos;
    public String matricula;
    public String email;
    public String contrasena;

    public Usuario() {
    }

    public Usuario(String nombre, String apellidos, String matricula, String email, String contrasena) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.matricula = matricula;
        this.email = email;
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
