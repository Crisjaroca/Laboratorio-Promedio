package com.example.promedioest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button continuarBtn;
    private EditText nombreEst;
    private EditText codigoEst;
    private Spinner materiasEst;
    private int duration = Toast.LENGTH_SHORT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd_estudiantes", null,1);

        nombreEst = findViewById(R.id.nombreEstu);
        codigoEst = findViewById(R.id.codigoEst);
        materiasEst = findViewById(R.id.materiasEst);

        this.continuarBtn = findViewById(R.id.volverBtn);
        this.continuarBtn.setOnClickListener(view -> {
            if (nombreEst.getText().toString().trim().isEmpty() && codigoEst.getText().toString().isEmpty()){
                avisoNombreCodigo();
            } else if(nombreEst.getText().toString().trim().isEmpty()){
                avisoNombre();
            } else if(codigoEst.getText().toString().isEmpty()){
                avisoCodigo();
            } else if(codigoEst.getText().length() < 6){
                avisoCodigoCorto();
            } else {
                Intent intent = new Intent(this, DatosActivity.class);
                intent.putExtra("NOMBRE_KEY", nombreEst.getText().toString().trim());
                intent.putExtra("CODIGO_KEY", codigoEst.getText().toString());
                intent.putExtra("MATERIA_KEY", materiasEst.getSelectedItem().toString());
                startActivity(intent);
            }
        });
    }

    private void avisoCodigoCorto() {
        Context context = getApplicationContext();
        CharSequence text = "El Código Requiere de 6 a 8 Dígitos";
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void avisoNombre() {
        Context context = getApplicationContext();
        CharSequence text = "Nombre Vacío";
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void avisoCodigo() {
        Context context = getApplicationContext();
        CharSequence text = "Código Vacío";
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void avisoNombreCodigo() {
        Context context = getApplicationContext();
        CharSequence text = "Nombre y Código Vacíos";
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}