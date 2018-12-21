package com.example.valdemar.myassistance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Loguin extends AppCompatActivity {
    private EditText CI, PSS;
    private String user, pss, usuario, contraseña;
    private TextView texto, reco, texto1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loguin);
        //CI = (EditText)findViewById(R.id.CII);
        //PSS =  (EditText)findViewById(R.id.PSS);
        texto = (TextView)findViewById(R.id.texto);
        reco = (TextView)findViewById(R.id.recom);
       
    }
    public void Sesion(View view){
        Intent adm = new Intent(this,Menu.class);
        startActivity(adm);
        finish();
    }
    /*public void Sesion(View view){
        user = "admin";
        pss = "admin";
        usuario = CI.getText().toString();
        contraseña = PSS.getText().toString();
        if(usuario.length()==0){
            Toast.makeText(this,"Ingrese Usuario",Toast.LENGTH_SHORT).show();
        }else if(contraseña.length()==0){
            Toast.makeText(this,"Ingrese Contraseña",Toast.LENGTH_SHORT).show();
        }
        if(usuario.compareTo(user) == 0){
            if(contraseña.compareTo(pss) == 0){
                Intent adm = new Intent(this,Menu.class);
                startActivity(adm);
                finish();
            }
            else
                Toast.makeText(this,"Contraseña Incorrecto",Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(this,"Usuario Incorrecto",Toast.LENGTH_SHORT).show();
    }*/
}
