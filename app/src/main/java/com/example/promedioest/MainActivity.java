package com.example.promedioest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

   private Button materiasBtn;
   private Button estudiantesBtn;
   private Button notasBtn;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_materias", null, 1);
   }

   public void onClick(View view){
        Intent intent=null;
        switch (view.getId()){
            case R.id.materiasBtn:
                intent = new Intent(MainActivity.this, MateriaMenuActivity.class);
                break;
            case R.id.notasBtn:
                intent = new Intent(MainActivity.this,NotaMenuActivity.class);
                break;
        }
        if (intent!=null){
            startActivity(intent);
        }
   }
}