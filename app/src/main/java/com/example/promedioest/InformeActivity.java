package com.example.promedioest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InformeActivity extends AppCompatActivity {
    private Button volverBtnInfo;
    private Button calcularBtn;

    private TextView nombreInfo;
    private TextView codigoInfo;
    private TextView materiaInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informe);

        nombreInfo = findViewById(R.id.nombreInfo);
        codigoInfo = findViewById(R.id.codigoInfo);
        materiaInfo = findViewById(R.id.materiaInfo);

        String nombre = getIntent().getStringExtra("NOMBRE_KEY");
        String codigo = getIntent().getStringExtra("CODIGO_KEY");
        String materia = getIntent().getStringExtra("MATERIA_KEY");
        nombreInfo.setText(nombre);
        codigoInfo.setText(codigo);
        materiaInfo.setText(materia);

        this.volverBtnInfo = findViewById(R.id.continuarBtn);
        this.volverBtnInfo.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            finish();
        });
    }
}