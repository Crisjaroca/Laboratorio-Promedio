package com.example.promedioest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DatosActivity extends AppCompatActivity {

    private Button volverBtn;
    private Button informeBtn;
    private Button guardarBtn;

    private int notas = 0;
    private int sumatoria = 0;

    private TextView nombreInfo;
    private TextView codigoInfo;
    private TextView materiaInfo;
    private TextView numeroNotas;
    private EditText notaInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        nombreInfo = findViewById(R.id.nombreInfo);
        codigoInfo = findViewById(R.id.codigoInfo);
        materiaInfo = findViewById(R.id.materiaInfo);
        numeroNotas = findViewById(R.id.numeroNotas);
        notaInfo = findViewById(R.id.notaInfo);

        String nombre = getIntent().getStringExtra("NOMBRE_KEY");
        String codigo = getIntent().getStringExtra("CODIGO_KEY");
        String materia = getIntent().getStringExtra("MATERIA_KEY");

        nombreInfo.setText(nombre);
        codigoInfo.setText(codigo);
        materiaInfo.setText(materia);

        this.guardarBtn = findViewById(R.id.guardarBtn);
        this.guardarBtn.setOnClickListener(view -> {

            notaInfo.setText(Integer.toString(sumatoria+=2));
            notaInfo.setHint("Digite una nota");
            notas++;
            numeroNotas.setText(Integer.toString(notas));
        });

        this.volverBtn = findViewById(R.id.volverBtn);
        this.volverBtn.setOnClickListener(view -> {
            finish();
        });

        this.informeBtn = findViewById(R.id.informeBtn);
        this.informeBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, InformeActivity.class);
            intent.putExtra("NOMBRE_KEY", nombre);
            intent.putExtra("CODIGO_KEY", codigo);
            intent.putExtra("MATERIA_KEY", materia);
            startActivity(intent);
        });
    }
}