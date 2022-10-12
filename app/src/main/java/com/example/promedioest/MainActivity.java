package com.example.promedioest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

   private Button materiasBtn;
   private Button estudiantesBtn;
   private Button notasBtn;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);

       ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_estudiantes", null, 1);

      this.materiasBtn = findViewById(R.id.materiasBtn);
      this.materiasBtn.setOnClickListener(view -> {
                 Intent intent = new Intent(this, MenuMateriasActivity.class);
                 startActivity(intent);
                }
      );
   }
}