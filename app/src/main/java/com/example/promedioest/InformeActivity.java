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
    private TextView numNotasInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informe);

        nombreInfo = findViewById(R.id.nombreInfo);
        codigoInfo = findViewById(R.id.codigoInfo);
        materiaInfo = findViewById(R.id.materiaInfo);
        numNotasInfo = findViewById(R.id.numNotasInfo);

        String nombre = getIntent().getStringExtra("NOMBRE_KEY");
        String codigo = getIntent().getStringExtra("CODIGO_KEY");
        String materia = getIntent().getStringExtra("MATERIA_KEY");
        String numero = getIntent().getStringExtra("NUMERO_KEY");

        nombreInfo.setText(nombre);
        codigoInfo.setText(codigo);
        materiaInfo.setText(materia);
        numNotasInfo.setText(numero);

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