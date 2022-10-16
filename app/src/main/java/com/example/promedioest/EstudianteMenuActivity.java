package com.example.promedioest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.promedioest.utilidades.Utilidades;

public class EstudianteMenuActivity extends AppCompatActivity {

    private EditText codigoEstudianteTxt, nombreEstudianteTxt, materiaEstudianteTxt;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_menu);

        conn = new ConexionSQLiteHelper(this, "bd_estudiantes", null, 1);

        codigoEstudianteTxt = findViewById(R.id.codigoEstudianteTxt);
        nombreEstudianteTxt = findViewById(R.id.nombreEstudianteTxt);
        materiaEstudianteTxt = findViewById(R.id.materiaEstudianteTxt);
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
                intent = new Intent(EstudianteMenuActivity.this, EstudianteListaIndividualActivity.class);
                break;
            case R.id.listarEstudianteCompletoBtn:
                intent = new Intent(EstudianteMenuActivity.this, EstudianteListaCompletaActivity.class);
                break;
        }
        if (intent!=null){
            startActivity(intent);
        }
    }

    private Boolean verificacionCasillas() {
        Boolean prueba;
        if (codigoEstudianteTxt.getText().toString().isEmpty() || nombreEstudianteTxt.getText().toString().isEmpty() || materiaEstudianteTxt.getText().toString().isEmpty()){
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

    private void modificar() {
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros = {codigoEstudianteTxt.getText().toString()};
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE_EST,nombreEstudianteTxt.getText().toString());
        values.put(Utilidades.CAMPO_MATERIA_EST,materiaEstudianteTxt.getText().toString());

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
            materiaEstudianteTxt.setText(cursor.getString(1));
            cursor.close();
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), "El código no existe", Toast.LENGTH_SHORT).show();
            limpiar();
        }
    }

    private void limpiar() {
        nombreEstudianteTxt.setText("");
        materiaEstudianteTxt.setText("");
    }

    private void agregar() {
        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Utilidades.CAMPO_ID_EST,codigoEstudianteTxt.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE_EST,nombreEstudianteTxt.getText().toString());
        values.put(Utilidades.CAMPO_MATERIA_EST,materiaEstudianteTxt.getText().toString());

        Long id_resultante = db.insert(Utilidades.TABLA_ESTUDIANTE,Utilidades.CAMPO_ID_EST,values);
        Toast.makeText(getApplicationContext(), "ID Registro: "+id_resultante, Toast.LENGTH_SHORT).show();
    }

}