package com.example.promedioest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private Button continuarBtn;
    private EditText nombreEst;
    private EditText codigoEst;
    private Spinner materiasEst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombreEst = findViewById(R.id.nombreEst);
        codigoEst = findViewById(R.id.codigoEst);
        materiasEst = findViewById(R.id.materiasEst);

        this.continuarBtn = findViewById(R.id.continuarBtn);
        this.continuarBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, DatosActivity.class);
            startActivity(intent);
        });
    }
}