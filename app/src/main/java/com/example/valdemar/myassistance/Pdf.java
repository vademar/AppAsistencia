package com.example.valdemar.myassistance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Pdf extends AppCompatActivity {
    private TextView fecha;
    private ListView LIST;
    private ArrayList<String> LISTAS, Rows;
    private ArrayAdapter<String> ADAPTER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        fecha = (TextView)findViewById(R.id.Fecha);
        LIST = (ListView)findViewById(R.id.LIS);
        LISTAS = (ArrayList<String>) getIntent().getStringArrayListExtra("Array");
        ADAPTER = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, LISTAS);
        LIST.setAdapter(ADAPTER);
        llenar();
    }
    public void llenar(){
        Bundle B = getIntent().getExtras();
        if(B != null){
            String fe=B.getString("Fecha");
            fecha.setText("Generado: "+fe);
        }
    }
}
