package com.example.promedioest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    final String CREAR_TABLA_MATERIA = "CREATE TABLE materias (id INTEGER, nombre TEXT)";
    final String CREAR_TABLA_ESTUDIANTE = "CREATE TABLE estudiantes (id INTEGER, nombre TEXT, materia TEXT)";

    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_MATERIA);
        db.execSQL(CREAR_TABLA_ESTUDIANTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS materia");
        db.execSQL("DROP TABLE IF EXISTS estudiantes");
        onCreate(db);
    }
}
