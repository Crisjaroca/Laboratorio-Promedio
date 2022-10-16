package com.example.promedioest.entidades;

import java.io.Serializable;

public class Estudiante implements Serializable {
    private String codigo;
    private String nombre;
    private String materia;

    public Estudiante(){

    }

    public Estudiante(String codigo, String nombre, String materia) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.materia = materia;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }
}
