package com.example.valdemar.myassistance;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;

import java.util.ArrayList;

public class Pdf extends AppCompatActivity {
    private TextView fecha;
    private TableLayout LIST;
    private ArrayList LISTAS;
    private String[] Rows;
    private ArrayAdapter<String> NUM,DAT,HOR;
    private ArrayList<String> num, dat,hor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        fecha = (TextView)findViewById(R.id.Fecha);
        LIST = (TableLayout) findViewById(R.id.LISTAS);
        LISTAS = (ArrayList) getIntent().getSerializableExtra("Array");
        dat = new ArrayList<String>();
        for(int indexR=0; indexR<LISTAS.size(); indexR++){
            TableRow Ro=new TableRow(this);
            String []row = (String[]) LISTAS.get(indexR);
            TextView textView;
            for(int indexC=0; indexC<3;indexC++){
                textView = new TextView(this);
                if(indexC ==2 ){
                    textView.setWidth(244);
                }
                textView.setHeight(78);
                textView.setGravity(Gravity.CENTER_VERTICAL);
                textView.setPadding(15,15,15,15);
                textView.setText(row[indexC]);
                textView.setTextSize(11);
                //textView.setTextColor(Color.BLUE);
                Ro.addView(textView);
            }
            LIST.addView(Ro);
        }
        DAT = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, dat);
        //LIST.setAdapter(DAT);
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
