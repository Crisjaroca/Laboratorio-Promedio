package com.example.promedioest.entidades;

public class Estudiante {
    private Integer codigo;
    private String nombre;
    private String materia;

    public Estudiante(Integer id, String nombre, String materia) {
        this.codigo = id;
        this.nombre = nombre;
        this.materia = materia;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
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
