package com.example.promedioest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NotaInformeActivity extends AppCompatActivity {

    private TextView nombreInfo;
    private TextView codigoInfo;
    private TextView materiaInfo;
    private TextView todasNotasInfo;
    private TextView promedio;
    private TextView mensajePromedio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota_informe);

        nombreInfo = findViewById(R.id.nombreInfo);
        codigoInfo = findViewById(R.id.codigoInfo);
        materiaInfo = findViewById(R.id.materiaInfo);
        todasNotasInfo = findViewById(R.id.todasNotasInfo);
        promedio = findViewById(R.id.promedio);
        mensajePromedio = findViewById(R.id.mensajeProm);

        Bundle recibeDatos = getIntent().getExtras();
        String[] nota = recibeDatos.getStringArray("NOTAS");
        int numero = recibeDatos.getInt("NUMERO_KEY");

        String nombre = getIntent().getStringExtra("NOMBRE_KEY");
        String codigo = getIntent().getStringExtra("CODIGO_KEY");
        String materia = getIntent().getStringExtra("MATERIA_KEY");

        int contador=0;
        double prom = 0;
        double suma = 0;

        for (int i = 0; i<nota.length; i++){
            if (nota[i]!=null){
                contador++;
            }
        }

        for (int i = 0; i<nota.length; i++){
            if (nota[i]!=null){
                suma += Double.valueOf(nota[i]);
            }
        }

        prom = suma/contador;


        nombreInfo.setText(nombre);
        codigoInfo.setText(codigo);
        materiaInfo.setText(materia);

        if (prom<3){
            mensajePromedio.setText("El estudiante NO Aprobó");
        } else{
            mensajePromedio.setText("El estudiante Aprobó");
        }

        String [] notaImp = new String[contador];

        for (int i = 0; i<notaImp.length; i++){
            if (nota[i]!=null){
                notaImp[i] = nota[i];
                todasNotasInfo.append(String.format("%.1f", Double.valueOf(notaImp[i])));
                todasNotasInfo.append("\n");
            }
        }

        promedio.setText(String.format("%.1f", prom));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cerrarBtn3:
                finish();
                break;
        }
    }
}