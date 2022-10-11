package com.example.promedioest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

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

        nombreInfo.setText(nombre);
        codigoInfo.setText(codigo);
        materiaInfo.setText(materia);
        promedio.setText(Integer.toString(prom));

        if (prom<30){
            mensajePromedio.setText("Perdiste Pelao");
        } else{
            mensajePromedio.setText("Pasaste Pelao");
        }
        todasNotasInfo.setText(nota[0] + "\n" + nota[1] + "\n" + nota[2]);

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