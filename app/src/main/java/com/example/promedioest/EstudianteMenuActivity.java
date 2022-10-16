package com.example.promedioest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.promedioest.entidades.Materia;
import com.example.promedioest.utilidades.Utilidades;

import java.util.ArrayList;

public class EstudianteMenuActivity extends AppCompatActivity {
    private EditText codigoEstudianteTxt, nombreEstudianteTxt;
    private Spinner comboMateriasEstudiante;

    ArrayList<String> listaMaterias;
    ArrayList<Materia> materiasList;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_menu);

        conn = new ConexionSQLiteHelper(this, "bd_materias", null, 1);

        codigoEstudianteTxt = findViewById(R.id.codigoEstudianteTxt);
        nombreEstudianteTxt = findViewById(R.id.nombreEstudianteTxt);
        comboMateriasEstudiante = findViewById(R.id.comboMateriasEstudiante);

        consultarListaMaterias();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, R.layout.spinner_item_tamano, listaMaterias);

        comboMateriasEstudiante.setAdapter(adaptador);
    }

    private void consultarListaMaterias() {
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
        listaMaterias = new ArrayList<String>();
        listaMaterias.add("Seleccione...");

        for (int i = 0; i< materiasList.size(); i++){
            listaMaterias.add(materiasList.get(i).getCodigo()+" - "+ materiasList.get(i).getNombre());
        }
    }

    public void onClick(View view){
        Intent intent=null;
        Boolean validacion;

        switch (view.getId()){
            case R.id.agregarEstudianteBtn:
                validacion = verificacionCasillas();
                if (validacion == false){
                    agregar();
                }
                break;
            case R.id.buscarEstudianteBtn:
                buscar();
                break;
            case R.id.eliminarEstudianteBtn:
                validacion = verificacionCasillas();
                if (validacion == false){
                    eliminar();
                }
                break;
            case R.id.modificarEstudianteBtn:
                validacion = verificacionCasillas();
                if (validacion == false){
                    modificar();
                }
                break;
            case R.id.listarEstudianteIndividualBtn:
                intent = new Intent(EstudianteMenuActivity.this, EstudiantesListaIndividualActivity.class);
                break;
            case R.id.listarEstudianteCompletolBtn:
                intent = new Intent(EstudianteMenuActivity.this, EstudianteListaCompletaActivity.class);
                break;
        }
        if (intent!=null){
            startActivity(intent);
        }
    }

    private void modificar() {
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros = {codigoEstudianteTxt.getText().toString()};
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE_EST,nombreEstudianteTxt.getText().toString());
        values.put(Utilidades.CAMPO_MATERIA_EST,comboMateriasEstudiante.getSelectedItem().toString());

        db.update(Utilidades.TABLA_ESTUDIANTE,values,Utilidades.CAMPO_ID_EST+"=?",parametros);
        Toast.makeText(getApplicationContext(), "Datos Actualizados", Toast.LENGTH_SHORT).show();
        db.close();
    }

    private void eliminar() {
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros = {codigoEstudianteTxt.getText().toString()};

        db.delete(Utilidades.TABLA_ESTUDIANTE,Utilidades.CAMPO_ID_EST+"=?",parametros);
        Toast.makeText(getApplicationContext(), "Estudiante Eliminado", Toast.LENGTH_SHORT).show();
        codigoEstudianteTxt.setText("");
        limpiar();
        db.close();
    }

    private void buscar() {
        SQLiteDatabase db=conn.getReadableDatabase();
        String[] parametros = {codigoEstudianteTxt.getText().toString()};
        String[] campos={Utilidades.CAMPO_NOMBRE_EST,Utilidades.CAMPO_MATERIA_EST};

        try {
            Cursor cursor = db.query(Utilidades.TABLA_ESTUDIANTE, campos,Utilidades.CAMPO_ID_EST+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            nombreEstudianteTxt.setText(cursor.getString(0));
            comboMateriasEstudiante.setSelection(0);
            cursor.close();
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), "El código no existe", Toast.LENGTH_SHORT).show();
            limpiar();
        }
    }

    private void limpiar() {
        nombreEstudianteTxt.setText("");
        comboMateriasEstudiante.setSelection(0);
    }

    private void agregar() {
        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Utilidades.CAMPO_ID_EST,codigoEstudianteTxt.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE_EST,nombreEstudianteTxt.getText().toString());
        values.put(Utilidades.CAMPO_MATERIA_EST,comboMateriasEstudiante.getSelectedItem().toString());

        Long id_resultante = db.insert(Utilidades.TABLA_ESTUDIANTE,Utilidades.CAMPO_ID_EST,values);
        Toast.makeText(getApplicationContext(), "ID Registro: "+id_resultante, Toast.LENGTH_SHORT).show();
        db.close();
    }

    private Boolean verificacionCasillas() {
        Boolean prueba;
        if (codigoEstudianteTxt.getText().toString().isEmpty() || nombreEstudianteTxt.getText().toString().isEmpty() || comboMateriasEstudiante.getSelectedItem().toString() == "Seleccione..."){
            Toast.makeText(getApplicationContext(), "Casillas de Ingreso Vacías ", Toast.LENGTH_SHORT).show();
            prueba = true;
        } else if (codigoEstudianteTxt.getText().length() < 6){
            Toast.makeText(getApplicationContext(), "El código Requiere Mínimo\n6 Dígitos ", Toast.LENGTH_SHORT).show();
            prueba = true;
        } else {
            prueba = false;
        }
        return prueba;
    }
}