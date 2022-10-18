package com.example.promedioest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NotaMenuActivity extends AppCompatActivity {

    private int notas = 0;
    private double sumatoria = 0;

    private String[] notasRecibidas = new String[50];

    private TextView nombreNota1Txt;
    private TextView codigoNota1Txt;
    private TextView materiaNota1Txt;
    private TextView numeroNotas;
    private EditText estudianteNotaTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota_menu);

        nombreNota1Txt = findViewById(R.id.nombreNota1Txt);
        codigoNota1Txt = findViewById(R.id.codigoNota1Txt);
        materiaNota1Txt = findViewById(R.id.materiaNota1Txt);
        numeroNotas = findViewById(R.id.numeroNotas);
        estudianteNotaTxt = findViewById(R.id.estudianteNotaTxt);

        String nombre = getIntent().getStringExtra("NOMBRE_KEY");
        String codigo = getIntent().getStringExtra("CODIGO_KEY");
        String materia = getIntent().getStringExtra("MATERIA_KEY");

        nombreNota1Txt.setText(nombre);
        codigoNota1Txt.setText(codigo);
        materiaNota1Txt.setText(materia);
    }

    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.agregarNotaBtn:
                agregarNota();
                break;
            case R.id.informePromedioBtn:
                if (notas == 0){
                    Toast.makeText(getApplicationContext(), "No Hay Notas Registradas", Toast.LENGTH_SHORT).show();
                } else {
                    String nombre = getIntent().getStringExtra("NOMBRE_KEY");
                    String codigo = getIntent().getStringExtra("CODIGO_KEY");
                    String materia = getIntent().getStringExtra("MATERIA_KEY");

                    Bundle enviaDatos = new Bundle();
                    enviaDatos.putStringArray("NOTAS",notasRecibidas);
                    enviaDatos.putInt("NUMERO_KEY", notas);
                    enviaDatos.putDouble("SUMATORIA_KEY",sumatoria);

                    intent = new Intent(NotaMenuActivity.this, NotaInformeActivity.class);
                    intent.putExtra("NOMBRE_KEY", nombre);
                    intent.putExtra("CODIGO_KEY", codigo);
                    intent.putExtra("MATERIA_KEY", materia);
                    intent.putExtras(enviaDatos);
                }
                break;
            case R.id.volverBtn5:
                finish();
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }

    private void agregarNota() {
        if (estudianteNotaTxt.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "No Hay Nota Para Guardar", Toast.LENGTH_SHORT).show();
        } else if (Double.valueOf(estudianteNotaTxt.getText().toString()) < 0.0 || Double.valueOf(estudianteNotaTxt.getText().toString()) > 5.0){
            Toast.makeText(getApplicationContext(), "Nota Fuera de Rango\n(0.0 - 5.0)", Toast.LENGTH_SHORT).show();
        } else {
            String notaStr = estudianteNotaTxt.getText().toString();
            double notaVal = Double.valueOf(notaStr);
            notasRecibidas[notas] = notaStr;
            sumatoria += notaVal;
            estudianteNotaTxt.setText("");
            estudianteNotaTxt.setHint("Ingrese una nota");
            notas++;
            numeroNotas.setText(Integer.toString(notas));
        }
    }
}