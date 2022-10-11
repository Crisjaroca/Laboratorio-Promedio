package com.example.promedioest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class DatosActivity extends AppCompatActivity {

    private Button volverBtn;
    private Button informeBtn;
    private Button guardarBtn;

    private int notas = 0;
    private double sumatoria = 0;
    private int duration = Toast.LENGTH_SHORT;

    private String[] notasRecibidas = new String[50];

    private TextView nombreInfo;
    private TextView codigoInfo;
    private TextView materiaInfo;
    private TextView numeroNotas;
    private EditText notaInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        nombreInfo = findViewById(R.id.nombreInfo);
        codigoInfo = findViewById(R.id.codigoInfo);
        materiaInfo = findViewById(R.id.materiaInfo);
        numeroNotas = findViewById(R.id.numeroNotas);
        notaInfo = findViewById(R.id.notaInfo);

        String nombre = getIntent().getStringExtra("NOMBRE_KEY");
        String codigo = getIntent().getStringExtra("CODIGO_KEY");
        String materia = getIntent().getStringExtra("MATERIA_KEY");

        nombreInfo.setText(nombre);
        codigoInfo.setText(codigo);
        materiaInfo.setText(materia);

        this.volverBtn = findViewById(R.id.volverBtn);
        this.volverBtn.setOnClickListener(view -> {
            finish();
        });

        this.informeBtn = findViewById(R.id.informeBtn);
        this.informeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (notas==0){
                    avisoRegistrar();
                } else {
                    Bundle enviaDatos = new Bundle();
                    enviaDatos.putStringArray("NOTAS",notasRecibidas);
                    enviaDatos.putInt("NUMERO_KEY", notas);
                    enviaDatos.putDouble("SUMATORIA_KEY",sumatoria);

                    Intent intent = new Intent(DatosActivity.this, InformeActivity.class);
                    intent.putExtra("NOMBRE_KEY", nombre);
                    intent.putExtra("CODIGO_KEY", codigo);
                    intent.putExtra("MATERIA_KEY", materia);
                    intent.putExtras(enviaDatos);
                    startActivity(intent);
                }
            }
        });
    }

    public void guardarDatos(View view){
        if (notaInfo.getText().toString().isEmpty()){
            avisoNota();
        } else if (Double.valueOf(notaInfo.getText().toString()) < 0.0 || Double.valueOf(notaInfo.getText().toString()) > 5.0){
            avisoLimite();
        } else {
            String notaStr = notaInfo.getText().toString();
            double notaVal = Double.valueOf(notaStr);
            notasRecibidas[notas] = notaStr;
            sumatoria += notaVal;
            notaInfo.setText("");
            notaInfo.setHint("Digite una nota");
            notas++;
            numeroNotas.setText(Integer.toString(notas));
        }
    }

    private void avisoLimite() {
        Context context = getApplicationContext();
        CharSequence text = "Nota Fuera de Rango\n(0.0 - 5.0)";
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void avisoNota() {
        Context context = getApplicationContext();
        CharSequence text = "No Hay Nota Para Guardar";
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void avisoRegistrar() {
        Context context = getApplicationContext();
        CharSequence text = "No Hay Notas Registradas";
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}