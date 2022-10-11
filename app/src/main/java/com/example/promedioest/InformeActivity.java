package com.example.promedioest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

public class InformeActivity extends AppCompatActivity {
    private Button volverBtnInfo;
    private Button calcularBtn;

    private TextView nombreInfo;
    private TextView codigoInfo;
    private TextView materiaInfo;
    private TextView todasNotasInfo;
    private TextView promedio;
    private TextView mensajePromedio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informe);

        nombreInfo = findViewById(R.id.nombreInfo);
        codigoInfo = findViewById(R.id.codigoInfo);
        materiaInfo = findViewById(R.id.materiaInfo);
        todasNotasInfo = findViewById(R.id.todasNotasInfo);
        promedio = findViewById(R.id.promedio);
        mensajePromedio = findViewById(R.id.mensajeProm);

        Bundle recibeDatos = getIntent().getExtras();
        String[] nota = recibeDatos.getStringArray("NOTAS");
        int numero = recibeDatos.getInt("NUMERO_KEY");
        int suma = recibeDatos.getInt("SUMATORIA_KEY");

        String nombre = getIntent().getStringExtra("NOMBRE_KEY");
        String codigo = getIntent().getStringExtra("CODIGO_KEY");
        String materia = getIntent().getStringExtra("MATERIA_KEY");
        int prom = suma/numero;
        int contador=0;

        nombreInfo.setText(nombre);
        codigoInfo.setText(codigo);
        materiaInfo.setText(materia);
        promedio.setText(Integer.toString(prom));

        if (prom<30){
            mensajePromedio.setText("El estudiante NO Aprobó");
        } else{
            mensajePromedio.setText("El estudiante Aprobó");
        }

        for (int i = 0; i<nota.length; i++){
            if (nota[i]!=null){
                contador++;
            }
        }

        String [] notaImp = new String[contador];

        for (int i = 0; i<notaImp.length; i++){
            if (nota[i]!=null){
                notaImp[i] = nota[i];
                todasNotasInfo.append(notaImp[i]);
                todasNotasInfo.append("\n");
            }
        }

        this.volverBtnInfo = findViewById(R.id.volverBtn);
        this.volverBtnInfo.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("NOMBRE_KEY", nombre);
            intent.putExtra("CODIGO_KEY", codigo);
            intent.putExtra("MATERIA_KEY", materia);
            finish();
        });
    }
}