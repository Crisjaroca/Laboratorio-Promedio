package com.example.promedioest.Materia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.promedioest.ConexionSQLiteHelper;
import com.example.promedioest.R;
import com.example.promedioest.utilidades.Utilidades;

public class AgregarMateriasActivity extends AppCompatActivity {

    private EditText codigoMateriaTxt;
    private EditText nombreMateriaTxt;
    private Button agregarMateriaBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_agregar_materias);

        codigoMateriaTxt = findViewById(R.id.codigoMateriaTxt);
        nombreMateriaTxt = findViewById(R.id.nombreMateriaTxt);

        this.agregarMateriaBtn = findViewById(R.id.agregarMateriaBtn);
        this.agregarMateriaBtn.setOnClickListener(view -> {
                    registroDeMateria();
                    //Intent intent = new Intent(this, MenuMateriasActivity.class);
                    //startActivity(intent);
                }
        );
    }

    private void registroDeMateria() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_materias", null, 1);

        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_ID_MAT, codigoMateriaTxt.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE_MAT, nombreMateriaTxt.getText().toString());

        Long idResultante = db.insert(Utilidades.TABLA_MATERIA,Utilidades.CAMPO_ID_MAT,values);

        //Toast.makeText(getApplicationContext(),"ID Registro: "+ idResultante,Toast.LENGTH_SHORT).show();

        //db.close();
    }
}