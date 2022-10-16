package com.example.promedioest;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.promedioest.entidades.Materia;
import com.example.promedioest.utilidades.Utilidades;

import java.util.ArrayList;

public class MateriaListaCompletaActivity extends AppCompatActivity {

    private ListView listViewMaterias;
    ArrayList<String> listaInformación;
    ArrayList<Materia> listaMaterias;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_lista_completa);

        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_materias",null,1);

        listViewMaterias = findViewById(R.id.listViewMaterias);

        consultarListaMaterias();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaInformación);
        listViewMaterias.setAdapter(adaptador);
    }

    private void consultarListaMaterias() {
        SQLiteDatabase db = conn.getReadableDatabase();

        Materia materia = null;
        listaMaterias = new ArrayList<Materia>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_MATERIA,null);

        while(cursor.moveToNext()){
            materia = new Materia();
            materia.setCodigo(cursor.getString(0));
            materia.setNombre(cursor.getString(1));

            listaMaterias.add(materia);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformación = new ArrayList<String>();

        for(int i = 0; i<listaMaterias.size(); i++){
            listaInformación.add("\nCódigo:\n"+listaMaterias.get(i).getCodigo()+ "\n\nNombre:\n"+listaMaterias.get(i).getNombre()+"\n");
        }
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.cerrarBtn:
                finish();
                break;
        }
    }
}