package com.example.valdemar.myassistance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Menu extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView escannerView;
    private String Dato;
    private TextView USER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
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
        Toast.makeText(this,"Precionando REGISTRO",Toast.LENGTH_SHORT).show();
    }
    public void PDF(View view){
        Toast.makeText(this,"Precionando PDF",Toast.LENGTH_SHORT).show();
    }
}