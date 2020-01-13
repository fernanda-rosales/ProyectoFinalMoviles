package com.example.proyectomoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AgregarIncidencia extends AppCompatActivity {

    EditText titulo,dia,periodo,lugar,riesgo,descripcion;
    Button agregarInci,tomarFoto;
    String tituloInci,diaInci,periodoInci,lugarInci,riesgoInci,descripcionInci;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_incidencia);

        titulo=(EditText) findViewById(R.id.editTextTituloInci);
        dia=(EditText) findViewById(R.id.editTextDia);
        periodo=(EditText) findViewById(R.id.editTextPeriodo);
        lugar=(EditText) findViewById(R.id.editTextLugar);
        riesgo=(EditText) findViewById(R.id.editTextRiesgo);
        descripcion=(EditText) findViewById(R.id.editTextDescripcion);
    }

    public void enviarDatosIncidencia(){
        tituloInci=titulo.getText().toString();
        diaInci=dia.getText().toString();
        periodoInci=periodo.getText().toString();
        lugarInci=lugar.getText().toString();
        riesgoInci=riesgo.getText().toString();
        descripcionInci=descripcion.getText().toString();
        registrarIncidencia(tituloInci,diaInci,periodoInci,lugarInci,riesgoInci,descripcionInci);


    }
    public void registrarIncidencia(String tituloInci,String diaInci,String periodoInci,String lugarInci,String riesgoInci,String descripcionInci){


    }
}
