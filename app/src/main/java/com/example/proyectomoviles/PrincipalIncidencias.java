package com.example.proyectomoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class PrincipalIncidencias extends AppCompatActivity {

    ImageView agregarInci,verInci,infoInci,salirInci;
    FirebaseAuth mCerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_incidencias);
        agregarInci=(ImageView) findViewById(R.id.imageViewAgregar);
        verInci=(ImageView) findViewById(R.id.imageViewVer);
        infoInci=(ImageView) findViewById(R.id.imageViewInfo);
        salirInci=(ImageView) findViewById(R.id.imageViewSalir);
        mCerrar=FirebaseAuth.getInstance();

        agregarInci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent agregar=new Intent(view.getContext(),AgregarIncidencia.class);
                startActivityForResult(agregar,0);
            }
        });

        verInci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ver=new Intent(view.getContext(),VerIncidencia.class);
                startActivityForResult(ver,0);
            }
        });

        infoInci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent info=new Intent(view.getContext(),InfoIncidencias.class);
                startActivityForResult(info,0);
            }
        });

       salirInci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mCerrar.signOut();
                Intent salir=new Intent(view.getContext(),MainActivity.class);
                startActivityForResult(salir,0);
            }
        });



    }
}
