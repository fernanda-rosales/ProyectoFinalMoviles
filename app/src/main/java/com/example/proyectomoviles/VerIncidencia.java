package com.example.proyectomoviles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyectomoviles.domain.Incidencia;
import com.example.proyectomoviles.fragment.IncidenciaFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VerIncidencia extends AppCompatActivity implements IncidenciaFragment.OnFragmentInteractionListener {

    TextView tituloVer, diaVer, periodoVer, lugarVer, riesgoVer, descripcionVer;
    ImageView mostrarFotoIn;
    DatabaseReference mRooot;
    List<Incidencia> incidencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_incidencia);
/*

        tituloVer = (TextView) findViewById(R.id.txtTituloInciInfo);
        diaVer = (TextView) findViewById(R.id.txtDiaInfo);
        periodoVer = (TextView) findViewById(R.id.txtPeriodoInfo);
        lugarVer = (TextView) findViewById(R.id.txtLugarInfo);
        riesgoVer = (TextView) findViewById(R.id.txtRiesgoInfo);
        descripcionVer = (TextView) findViewById(R.id.txtDescripcionInfo);
        mostrarFotoIn = (ImageView) findViewById(R.id.imageViewMostrarFotoInfo);
*/

        mRooot = FirebaseDatabase.getInstance().getReference();
        mRooot.child("Incidencia").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                incidencias = new ArrayList<Incidencia>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Incidencia incidencia = snapshot.getValue(Incidencia.class);
                    Log.e("Datos ejemplo yon:", "" + incidencia.getTitulo());
                    incidencias.add(incidencia);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
