package com.example.valdemar.myassistance;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.Calendar;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Menu extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView escannerView;
    private String Dato, F, NomPdf;
    private TextView USER;
    private EditText NOMPDF;
    private ListView LIST;
    private ArrayList<String[]> ARRAYLIST, rows;
    private ArrayAdapter<String[]> ADAPTER;
    private String[]header;
    private TemplatePDF templatePDF;
    private Button Reg;
    private int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Reg = (Button)findViewById(R.id.registros);
        LIST = (ListView)findViewById(R.id.USUARIOS);
        ARRAYLIST = new ArrayList<String[]>();
        rows = new ArrayList<String[]>();
        ADAPTER = new ArrayAdapter<String[]>(this,R.layout.custom, ARRAYLIST);
        LIST.setAdapter(ADAPTER);
        NOMPDF = (EditText)findViewById(R.id.tit);
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);
        F = String.valueOf(dia+"/"+(mes+1)+"/"+year);
        templatePDF = new TemplatePDF(getApplicationContext());
        templatePDF.createTable(header,getClients());
    }
    public void EscanerQR(View view){
        NomPdf = NOMPDF.getText().toString();
        if(NomPdf.length()!=0 && NOMPDF.getText().toString()!=null){
            escannerView =new ZXingScannerView(this);
            setContentView(escannerView);
            escannerView.setResultHandler(this);
            escannerView.startCamera();
            NOMPDF.setText(NomPdf);
        }
        else
            Toast.makeText(this,"INGRESE UN NOMBRE, PARA INICIAR POR FAVOR",Toast.LENGTH_LONG).show();
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
            //verif();
            String num = String.valueOf(i);
            String Time = String.valueOf(hora+":"+min);
            ARRAYLIST.add(new String[]{num,Dato,Time});
            ADAPTER.notifyDataSetChanged();
            rows = (ArrayList<String[]>) ARRAYLIST.clone();
            Reg.setEnabled(false);
            Toast.makeText(this,i+" Usuario, Registrado",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Aún no ha Hecho lectura de Datos", Toast.LENGTH_SHORT).show();
            Reg.setEnabled(true);
        }
    }
    private void verif(){

    }
    private ArrayList<String[]>getClients(){
        return rows;
    }
    public void verPDF(View view){
        Intent intent = new Intent(this, Pdf.class);
        intent.putExtra("titulo", NomPdf);
        intent.putExtra("Fecha", F);
        intent.putExtra("Array", rows);
        startActivity(intent);
    }
    public void createPDF(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Al generar el PDF, la Aplicación Dara por concluida su Función y se CERRARA");
        builder.setTitle("¡AVISO!");
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                templatePDF.openDocument(NomPdf);
                templatePDF.appviewPdF(Menu.this);
                templatePDF.addTitles(NomPdf,"Usuarios",F);
                header= new String[]{"Nº","Datos Personales","Hr Ingreso"};
                templatePDF.createTable(header,getClients());
                templatePDF.closeDocument();
                finish();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Toda la información se Perderá, si deja la Aplicación");
        builder.setTitle("¡ALTO!");
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
