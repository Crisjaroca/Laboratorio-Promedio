package com.example.promedioest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.promedioest.entidades.Estudiante;
import com.example.promedioest.utilidades.Utilidades;

import java.util.ArrayList;

public class NotaEstudianteActivity extends AppCompatActivity {

    private Spinner comboEstudiantesNota;
    private TextView codigoEstudianteNota, nombreNotaTxt, materiaNotaTxt;
    ArrayList<String> listaEstudiantes;
    ArrayList<Estudiante> estudiantesList;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota_estudiante);

        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_materias", null, 1);

        codigoEstudianteNota = findViewById(R.id.codigoEstudianteNota);
        nombreNotaTxt = findViewById(R.id.nombreNotaTxt);
        materiaNotaTxt = findViewById(R.id.materiaNotaTxt);

        comboEstudiantesNota = findViewById(R.id.comboEstudiantesNota);

        consultarListaEstudiantes();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, R.layout.spinner_item_tamano, listaEstudiantes);

        comboEstudiantesNota.setAdapter(adaptador);

        comboEstudiantesNota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                if (position != 0) {
                    codigoEstudianteNota.setText(estudiantesList.get(position - 1).getCodigo().toString());
                    nombreNotaTxt.setText(estudiantesList.get(position - 1).getNombre());
                    materiaNotaTxt.setText(estudiantesList.get(position - 1).getMateria());
                } else {
                    codigoEstudianteNota.setText("");
                    nombreNotaTxt.setText("");
                    materiaNotaTxt.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void consultarListaEstudiantes() {
        SQLiteDatabase db = conn.getReadableDatabase();

        Estudiante estudiante = null;
        estudiantesList = new ArrayList<Estudiante>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_ESTUDIANTE, null);

        while (cursor.moveToNext()) {
            estudiante = new Estudiante();
            estudiante.setCodigo(cursor.getString(0));
            estudiante.setNombre(cursor.getString(1));
            estudiante.setMateria(cursor.getString(2));

            estudiantesList.add(estudiante);
        }

        obtenerLista();
    }

    private void obtenerLista() {
        listaEstudiantes = new ArrayList<String>();
        listaEstudiantes.add("Seleccione...");

        for (int i = 0; i < estudiantesList.size(); i++) {
            listaEstudiantes.add(estudiantesList.get(i).getCodigo());
        }
    }

    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.volverBtn:
                finish();
                break;
            case R.id.continuarBtn:
                if (comboEstudiantesNota.getSelectedItem().equals("Seleccione...")){
                    Toast.makeText(getApplicationContext(), "Estudiante No Seleccionado", Toast.LENGTH_SHORT).show();
                } else {
                    intent = new Intent(NotaEstudianteActivity.this,NotaMenuActivity.class);
                    intent.putExtra("NOMBRE_KEY", nombreNotaTxt.getText().toString().trim());
                    intent.putExtra("CODIGO_KEY", codigoEstudianteNota.getText().toString());
                    intent.putExtra("MATERIA_KEY", materiaNotaTxt.getText().toString());
                }
                break;
        }
        if (intent!=null){
            startActivity(intent);
        }
    }
}