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

import com.example.promedioest.entidades.Materia;
import com.example.promedioest.utilidades.Utilidades;

import java.util.ArrayList;

public class MateriaListaIndividualActivity extends AppCompatActivity {

    private Spinner comboMaterias;
    private TextView codigoInfoTxt, nombreInfoTxt;
    ArrayList<String> listaEstudiantes;
    ArrayList<Materia> materiasList;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_lista_individual);

        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_materias",null,1);

        codigoInfoTxt = findViewById(R.id.codigoInfoTxt);
        nombreInfoTxt = findViewById(R.id.nombreInfoTxt);

        comboMaterias = findViewById(R.id.comboMaterias);

        consultarListaEstudiantes();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, R.layout.spinner_item_tamano, listaEstudiantes);

        comboMaterias.setAdapter(adaptador);

        comboMaterias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                if (position!=0) {
                    codigoInfoTxt.setText(materiasList.get(position - 1).getCodigo().toString());
                    nombreInfoTxt.setText(materiasList.get(position - 1).getNombre());
                } else {
                    codigoInfoTxt.setText("");
                    nombreInfoTxt.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void consultarListaEstudiantes() {
        SQLiteDatabase db = conn.getReadableDatabase();

        Materia materia = null;
        materiasList = new ArrayList<Materia>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+Utilidades.TABLA_MATERIA,null);

        while (cursor.moveToNext()){
            materia = new Materia();
            materia.setCodigo(cursor.getString(0));
            materia.setNombre(cursor.getString(1));

            materiasList.add(materia);
        }

        obtenerLista();
    }

    private void obtenerLista() {
        listaEstudiantes = new ArrayList<String>();
        listaEstudiantes.add("Seleccione...");

        for (int i = 0; i< materiasList.size(); i++){
            listaEstudiantes.add(materiasList.get(i).getCodigo()+" - "+ materiasList.get(i).getNombre());
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