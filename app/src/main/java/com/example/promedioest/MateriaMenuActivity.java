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

public class MateriaMenuActivity extends AppCompatActivity {

    private EditText codigoMateriaTxt, nombreMateriaTxt;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_menu);

        conn = new ConexionSQLiteHelper(this, "bd_materias", null, 1);

        codigoMateriaTxt = findViewById(R.id.codigoMateriaTxt);
        nombreMateriaTxt = findViewById(R.id.nombreMateriaTxt);
    }

    public void onClick(View view){
        Intent intent=null;
        Boolean validacion;

        switch (view.getId()){
            case R.id.agregarMateriaBtn:
                validacion = verificacionCasillas();
                if (validacion == false){
                    agregar();
                }
                break;
            case R.id.buscarMateriaBtn:
                buscar();
                break;
            case R.id.eliminarMateriaBtn:
                validacion = verificacionCasillas();
                if (validacion == false){
                    eliminar();
                }
                break;
            case R.id.modificarMateriaBtn:
                validacion = verificacionCasillas();
                if (validacion == false){
                    modificar();
                }
                break;
            case R.id.listarMateriaIndividualBtn:
                intent = new Intent(MateriaMenuActivity.this, MateriaListaIndividualActivity.class);
                break;
            case R.id.listarMateriaCompletaBtn:
                intent = new Intent(MateriaMenuActivity.this, MateriaListaCompletaActivity.class);
                break;
            case R.id.volverBtn3:
                finish();
                break;
        }
        if (intent!=null){
            startActivity(intent);
        }
    }

    private Boolean verificacionCasillas() {
        Boolean prueba;
        if (codigoMateriaTxt.getText().toString().isEmpty() || nombreMateriaTxt.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Casillas de Ingreso Vacías ", Toast.LENGTH_SHORT).show();
            prueba = true;
        } else if (codigoMateriaTxt.getText().length() < 5){
            Toast.makeText(getApplicationContext(), "El código Requiere 5 Dígitos ", Toast.LENGTH_SHORT).show();
            prueba = true;
        } else {
            prueba = false;
        }
        return prueba;
    }

    private void modificar() {
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros = {codigoMateriaTxt.getText().toString()};
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE_MAT, nombreMateriaTxt.getText().toString());
        db.update(Utilidades.TABLA_MATERIA,values,Utilidades.CAMPO_ID_MAT +"=?",parametros);
        Toast.makeText(getApplicationContext(), "Datos Actualizados", Toast.LENGTH_SHORT).show();
        db.close();
    }

    private void eliminar() {
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros = {codigoMateriaTxt.getText().toString()};

        db.delete(Utilidades.TABLA_MATERIA,Utilidades.CAMPO_ID_MAT +"=?",parametros);
        Toast.makeText(getApplicationContext(), "Materia Eliminado", Toast.LENGTH_SHORT).show();
        codigoMateriaTxt.setText("");
        limpiar();
        db.close();
    }

    private void buscar() {
        SQLiteDatabase db=conn.getReadableDatabase();
        String[] parametros = {codigoMateriaTxt.getText().toString()};
        String[] campos={Utilidades.CAMPO_NOMBRE_MAT};

        try {
            Cursor cursor = db.query(Utilidades.TABLA_MATERIA, campos,Utilidades.CAMPO_ID_MAT +"=?",parametros,null,null,null);
            cursor.moveToFirst();
            nombreMateriaTxt.setText(cursor.getString(0));
            cursor.close();
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), "El código no existe", Toast.LENGTH_SHORT).show();
            limpiar();
        }
    }

    private void limpiar() {
        nombreMateriaTxt.setText("");
    }

    private void agregar() {
        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Utilidades.CAMPO_ID_MAT, codigoMateriaTxt.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE_MAT, nombreMateriaTxt.getText().toString());

        Long id_resultante = db.insert(Utilidades.TABLA_MATERIA,Utilidades.CAMPO_ID_MAT,values);
        Toast.makeText(getApplicationContext(), "ID Registro: "+id_resultante, Toast.LENGTH_SHORT).show();
    }

}