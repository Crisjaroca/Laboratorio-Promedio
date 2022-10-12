package com.example.promedioest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MenuMateriasActivity extends AppCompatActivity {

    private Button listarMateriasBtn;
    private Button agregarMateriasBtn;
    private Button modificarMateriasBtn;
    private Button eliminarMateriasBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_materias);

        this.agregarMateriasBtn = findViewById(R.id.agregarMateriasBtn);
        this.agregarMateriasBtn.setOnClickListener(view -> {
                    Intent intent = new Intent(this, AgregarMateriasActivity.class);
                    startActivity(intent);
                }
        );
    }
}