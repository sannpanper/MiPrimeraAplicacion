package com.example.tema5listadosymenus;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.gridview);

        // Datos de ejemplo para la lista
        List<String> data = Arrays.asList(
                "España", "Francia", "Italia", "Alemania",
                "Portugal", "Bélgica", "Países Bajos",
                "Grecia", "Austria", "Polonia"
        );

        // Configurar el ListView y asignarle el adaptador
        //ListView listView = findViewById(R.id.listView);
        TextView texto = findViewById(R.id.textView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);

        //listView.setAdapter(adapter);

        //listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           // @Override
            //public void onItemClick(AdapterView<?> parent, View view, int posicion, long l) {

                // Obtener el texto del elemento pulsado
               // String elemento = (String) parent.getItemAtPosition(posicion);

                // Mostrar el país en el TextView
               // texto.setText(elemento);
           // }
       // });

        // Configurar el ListView y asignarle et adaptador
        GridView listado = findViewById(R.id.gridView);

        listado.setAdapter(adapter);

        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicion, long l) {
                //Obtener el texto del elemento pulsado
                String elemento = (String) parent.getItemAtPosition(posicion);
                texto.setText(elemento);
            }
        });
    }
}
