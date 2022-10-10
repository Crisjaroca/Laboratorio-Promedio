package com.example.promedioest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class DatosActivity extends AppCompatActivity {

    private Button volverBtn;
    private Button calcularBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        this.volverBtn = findViewById(R.id.volverBtn);
        this.volverBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            finish();
        });
        this.calcularBtn = findViewById(R.id.calcularBtn);
        this.calcularBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, InformeActivity.class);
            startActivity(intent);
        });
    }
}