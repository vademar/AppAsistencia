package com.example.valdemar.myassistance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;

import java.util.ArrayList;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Menu extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView escannerView;
    private String Dato;
    private TextView USER;
    private ListView LIST;
    private ArrayList<String> ARRAYLIST;
    private ArrayAdapter<String> ADAPTER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        LIST = (ListView)findViewById(R.id.USUARIOS);
        ARRAYLIST = new ArrayList<String>();
        ARRAYLIST.add("juan carlos");
        ADAPTER = new ArrayAdapter<String>(this,R.layout.custom, ARRAYLIST);
        LIST.setAdapter(ADAPTER);
    }
    public void EscanerQR(View view){
        escannerView =new ZXingScannerView(this);
        setContentView(escannerView);
        escannerView.setResultHandler(this);
        escannerView.startCamera();
    }
    @Override
    public void handleResult(Result result) {
        Dato = result.getText();
        setContentView(R.layout.activity_menu);
        escannerView.stopCamera();
        USER= (TextView)findViewById(R.id.USER);
        USER.setText(Dato);
    }
    public void Registrar(View view){
        if(Dato != null){
            ARRAYLIST.add(Dato);
            ADAPTER.notifyDataSetChanged();
            Toast.makeText(this,"ARRAYLIST: "+ARRAYLIST,Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this,"No ha leido Datos",Toast.LENGTH_SHORT).show();
    }
    public void createPDF(View view){
        Toast.makeText(this,"Precionando PDF",Toast.LENGTH_SHORT).show();
    }
}
