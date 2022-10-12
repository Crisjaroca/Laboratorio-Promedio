package com.example.promedioest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AgregarMateriasActivity extends AppCompatActivity {

    private EditText codigoMateria;
    private EditText nombreMateria;
    private Button agregarMateriaBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_materias);
    }
}