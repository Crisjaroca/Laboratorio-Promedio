package com.example.promedioest;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.promedioest.entidades.Estudiante;
import com.example.promedioest.utilidades.Utilidades;

import java.util.ArrayList;

public class EstudianteListaCompletaActivity extends AppCompatActivity {

    ListView listViewEstudiantes;

    ArrayList<String> listaInformacion;
    ArrayList<Estudiante> listaEstudiantes;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_lista_completa);

        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_estudiantes",null,1);

        listViewEstudiantes = findViewById(R.id.listViewEstudiantes);

        consultarListaEstudiantes();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaInformacion);

        listViewEstudiantes.setAdapter(adaptador);
    }

    private void consultarListaEstudiantes() {
        SQLiteDatabase db = conn.getReadableDatabase();

        Estudiante estudiante = null;
        listaEstudiantes = new ArrayList<Estudiante>();

        Cursor cursor = db.rawQuery(" SELECT * FROM "+ Utilidades.TABLA_ESTUDIANTE,null);

        while (cursor.moveToNext()){
            estudiante = new Estudiante();
            estudiante.setCodigo(cursor.getString(0));
            estudiante.setNombre(cursor.getString(1));
            estudiante.setMateria(cursor.getString(2));

            listaEstudiantes.add(estudiante);
        }

        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();

        for (int i = 0; i<listaEstudiantes.size(); i++){
            listaInformacion.add("\nCódigo:\n"+listaEstudiantes.get(i).getCodigo()+"\n\nNombre:\n"+listaEstudiantes.get(i).getNombre()+"\n\nMateria\n"+listaEstudiantes.get(i).getMateria()+"\n");
        }
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.cerrarBtn:
                finish();
                break;
        }
    }
}