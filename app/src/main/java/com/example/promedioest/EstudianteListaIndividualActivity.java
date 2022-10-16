package com.example.promedioest;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.promedioest.entidades.Estudiante;
import com.example.promedioest.utilidades.Utilidades;

import java.util.ArrayList;

public class EstudianteListaIndividualActivity extends AppCompatActivity {

    private Spinner comboEstudiantes;
    private TextView codigoInfoTxt, nombreInfoTxt, materiaInfoTxt;
    ArrayList<String> listaEstudiantes;
    ArrayList<Estudiante> estudiantesList;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_lista_individual);

        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_estudiantes",null,1);

        codigoInfoTxt = findViewById(R.id.codigoInfoTxt);
        nombreInfoTxt = findViewById(R.id.nombreInfoTxt);
        materiaInfoTxt = findViewById(R.id.materiaInfoTxt);

        comboEstudiantes = findViewById(R.id.comboEstudiantes);

        consultarListaEstudiantes();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, R.layout.spinner_item_tamano, listaEstudiantes);

        comboEstudiantes.setAdapter(adaptador);

        comboEstudiantes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                if (position!=0) {
                    codigoInfoTxt.setText(estudiantesList.get(position - 1).getCodigo().toString());
                    nombreInfoTxt.setText(estudiantesList.get(position - 1).getNombre());
                    materiaInfoTxt.setText(estudiantesList.get(position - 1).getMateria());
                } else {
                    codigoInfoTxt.setText("");
                    nombreInfoTxt.setText("");
                    materiaInfoTxt.setText("");
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

        Cursor cursor = db.rawQuery("SELECT * FROM "+Utilidades.TABLA_ESTUDIANTE,null);

        while (cursor.moveToNext()){
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

        for (int i = 0; i< estudiantesList.size(); i++){
            listaEstudiantes.add(estudiantesList.get(i).getCodigo()+" - "+estudiantesList.get(i).getNombre());
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