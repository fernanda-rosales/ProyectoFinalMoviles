package com.example.proyectomoviles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VerIncidencia extends AppCompatActivity {

    TextView tituloVer,diaVer,periodoVer,lugarVer,riesgoVer,descripcionVer;
    ImageView mostrarFotoIn;
    DatabaseReference mRooot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_incidencia);

        tituloVer=(TextView) findViewById(R.id.txtTituloInciInfo);
        diaVer=(TextView) findViewById(R.id.txtDiaInfo);
        periodoVer=(TextView) findViewById(R.id.txtPeriodoInfo);
        lugarVer=(TextView) findViewById(R.id.txtLugarInfo);
        riesgoVer=(TextView) findViewById(R.id.txtRiesgoInfo);
        descripcionVer=(TextView) findViewById(R.id.txtDescripcionInfo);
        mostrarFotoIn=(ImageView) findViewById(R.id.imageViewMostrarFotoInfo);

        mRooot=FirebaseDatabase.getInstance().getReference();
        mRooot.child("Incidencia").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Log.e("Datos:",""+snapshot.getValue());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
