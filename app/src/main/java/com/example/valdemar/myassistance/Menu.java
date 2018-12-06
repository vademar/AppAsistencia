package com.example.valdemar.myassistance;

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
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import com.itextpdf.text.BadElementException;

public class Menu extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView escannerView;
    private String Dato;
    private TextView USER;
    private ListView LIST;
    private ArrayList<String> ARRAYLIST;
    private ArrayList<String> rows;
    private ArrayAdapter<String> ADAPTER;
    private String[]header = {"Datos Personales"};
    private TemplatePDF templatePDF;
    private String shorText = "hola";
    private Button imprime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        imprime =(Button)findViewById(R.id.GenPDF);
        LIST = (ListView)findViewById(R.id.USUARIOS);
        ARRAYLIST = new ArrayList<String>();
        rows = new ArrayList<>();
        ADAPTER = new ArrayAdapter<String>(this,R.layout.custom, ARRAYLIST);
        LIST.setAdapter(ADAPTER);

        templatePDF = new TemplatePDF(getApplicationContext());
        templatePDF.openDocument();
        templatePDF.addTitles("Registro","Usuarios","06/12/18");
        templatePDF.createTable(header,getClients());
        //templatePDF.addParagraph(shorText);
        templatePDF.closeDocument();
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
            Toast.makeText(this,"rows: "+rows,Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this,"No ha leido Datos",Toast.LENGTH_SHORT).show();
    }
    public void createPDF(View view){
        templatePDF.viewPdF();
        //templatePDF.appviewPdF(this);
        Toast.makeText(this,"Precionando PDF",Toast.LENGTH_SHORT).show();
    }
    private ArrayList<String>getClients(){
        rows.add("Primero de la casa"+"\n"+"Pedro");
        rows.add("Segunda de la casa"+"\n"+"Romero");
        rows.add("Tercera de la casa"+"\n"+"Juan");
        //ARRAYLIST.add(new String[]{USER.getText().toString()});
        return rows;
    }
}
