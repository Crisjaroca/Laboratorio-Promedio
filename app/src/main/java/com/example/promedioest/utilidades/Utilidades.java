package com.example.promedioest.utilidades;

public class Utilidades {

    //Constantes tabla Estudiante
    public static final String TABLA_ESTUDIANTE = "estudiante";
    public static final String CAMPO_ID_EST = "id";
    public static final String CAMPO_NOMBRE_EST = "nombre";
    public static final String CAMPO_MATERIA_EST = "materia";

    public static final String CREAR_TABLA_ESTUDIANTE="CREATE TABLE " +TABLA_ESTUDIANTE+ " (" +CAMPO_ID_EST+" INTEGER, " +CAMPO_NOMBRE_EST+" TEXT, " +CAMPO_MATERIA_EST+ " TEXT)";
}
