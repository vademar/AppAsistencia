package com.example.valdemar.myassistance;

import android.content.Intent;
import android.graphics.Bitmap;
import android.inputmethodservice.Keyboard;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.Result;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import com.itextpdf.text.BadElementException;

import javax.crypto.AEADBadTagException;

public class Menu extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView escannerView;
    private String Dato,F;
    private TextView USER;
    private ListView LIST;
    private ArrayList<String[]> ARRAYLIST, rows;
    private ArrayAdapter<String[]> ADAPTER;
    private String[]header;
    private TemplatePDF templatePDF;
    private Button Reg;
    private int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Reg = (Button)findViewById(R.id.registros);
        LIST = (ListView)findViewById(R.id.USUARIOS);
        ARRAYLIST = new ArrayList<String[]>();
        rows = new ArrayList<String[]>();
        ADAPTER = new ArrayAdapter<String[]>(this,R.layout.custom, ARRAYLIST);
        LIST.setAdapter(ADAPTER);
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);
        F = String.valueOf(dia+"/"+mes+"/"+year);
        templatePDF = new TemplatePDF(getApplicationContext());
        templatePDF.openDocument();
        templatePDF.addTitles("Registro","Usuarios",F);
        templatePDF.createTable(header,getClients());
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
        Reg = (Button)findViewById(R.id.registros);
        Calendar c = Calendar.getInstance();
        int hora = c.get(Calendar.HOUR);
        int min = c.get(Calendar.MINUTE);
        if(Dato != null){
            i++;
            String num = String.valueOf(i);
            String Time = String.valueOf(hora+":"+min);
            ARRAYLIST.add(new String[]{num,Dato,Time});
            ADAPTER.notifyDataSetChanged();
            rows = (ArrayList<String[]>) ARRAYLIST.clone();
            Reg.setEnabled(false);
            Toast.makeText(this,i+" Usuario Ya Registrado",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "No ha leido Datos", Toast.LENGTH_SHORT).show();
            Reg.setEnabled(true);
        }
    }
    private ArrayList<String[]>getClients(){
        return rows;
    }
    public void verPDF(View view){
        Intent intent = new Intent(this, Pdf.class);
        intent.putExtra("Fecha", F);
        intent.putExtra("Array", rows);
        startActivity(intent);
    }
    public void createPDF(View view){
        templatePDF.appviewPdF(this);
        header= new String[]{"Nº","Datos Personales","Hr Ingreso"};
        templatePDF.createTable(header,getClients());
        templatePDF.closeDocument();
    }
}
