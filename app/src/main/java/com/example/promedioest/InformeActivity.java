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
    private TextView todasNotasInfo;
    private TextView notaSumaInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informe);

        nombreInfo = findViewById(R.id.nombreInfo);
        codigoInfo = findViewById(R.id.codigoInfo);
        materiaInfo = findViewById(R.id.materiaInfo);
        numNotasInfo = findViewById(R.id.numNotasInfo);
        todasNotasInfo = findViewById(R.id.todasNotasInfo);
        notaSumaInfo = findViewById(R.id.notaSumaInfo);

        String nombre = getIntent().getStringExtra("NOMBRE_KEY");
        String codigo = getIntent().getStringExtra("CODIGO_KEY");
        String materia = getIntent().getStringExtra("MATERIA_KEY");
        String numero = getIntent().getStringExtra("NUMERO_KEY");
        String suma = getIntent().getStringExtra("SUMATORIA_KEY");

        nombreInfo.setText(nombre);
        codigoInfo.setText(codigo);
        materiaInfo.setText(materia);
        numNotasInfo.setText(numero);
        notaSumaInfo.setText(suma);

        Bundle recibeDatos = getIntent().getExtras();
        String[] nota = recibeDatos.getStringArray("NOTAS");

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