package com.example.promedioest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class InformeActivity extends AppCompatActivity {
    private Button volverBtnInfo;
    private Button calcularBtn;

    private EditText nombreInfo;
    private EditText codigoInfo;
    private EditText materiaInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informe);

        this.volverBtnInfo = findViewById(R.id.volverBtnInfo);
        this.volverBtnInfo.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            finish();
        });
    }
}