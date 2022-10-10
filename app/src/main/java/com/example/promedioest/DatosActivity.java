package com.example.promedioest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class DatosActivity extends AppCompatActivity {

    private Button volverBtn;
    private Button informeBtn;
    private Button guardarBtn;

    private int notas = 0;
    private int sumatoria = 0;

    private ArrayList notasRecibidas = new ArrayList();

    private TextView nombreInfo;
    private TextView codigoInfo;
    private TextView materiaInfo;
    private TextView numeroNotas;
    private TextView notaSuma;
    private TextView todasNotasDatos;
    private EditText notaInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        nombreInfo = findViewById(R.id.nombreInfo);
        codigoInfo = findViewById(R.id.codigoInfo);
        materiaInfo = findViewById(R.id.materiaInfo);
        numeroNotas = findViewById(R.id.numeroNotas);
        notaSuma = findViewById(R.id.notaSumaDatos);
        notaInfo = findViewById(R.id.notaInfo);
        todasNotasDatos = findViewById(R.id.todasNotasDatos);

        String nombre = getIntent().getStringExtra("NOMBRE_KEY");
        String codigo = getIntent().getStringExtra("CODIGO_KEY");
        String materia = getIntent().getStringExtra("MATERIA_KEY");

        nombreInfo.setText(nombre);
        codigoInfo.setText(codigo);
        materiaInfo.setText(materia);

        this.volverBtn = findViewById(R.id.volverBtn);
        this.volverBtn.setOnClickListener(view -> {
            finish();
        });

        this.informeBtn = findViewById(R.id.informeBtn);
        this.informeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle enviaDatos = new Bundle();
                enviaDatos.putString("NOTAS",todasNotasDatos.getText().toString());

                Intent intent = new Intent(DatosActivity.this, InformeActivity.class);
                intent.putExtra("NOMBRE_KEY", nombre);
                intent.putExtra("CODIGO_KEY", codigo);
                intent.putExtra("MATERIA_KEY", materia);
                intent.putExtra("NUMERO_KEY" , numeroNotas.getText().toString());
                intent.putExtra("SUMATORIA_KEY", sumatoria);
                intent.putExtra("TODAS_NOTAS", notasRecibidas);
                intent.putExtras(enviaDatos);
                startActivity(intent);
            }
        });
    }

    public void guardarDatos(View view){
        String notaStr = notaInfo.getText().toString();
        int notaVal = Integer.parseInt(notaStr);
        todasNotasDatos.setText(notaInfo.getText());

        notasRecibidas.add(notaVal);

        notaInfo.setText(Integer.toString(sumatoria += Integer.parseInt(notasRecibidas.get(notas).toString())));
        notaSuma.setText(notaInfo.getText());
        notaInfo.setHint("Digite una nota");
        notas++;
        numeroNotas.setText(Integer.toString(notas));
    }
}