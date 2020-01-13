package com.example.proyectomoviles;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.proyectomoviles.domain.Incidencia;
import com.example.proyectomoviles.domain.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AgregarIncidencia extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    EditText titulo,dia,periodo,lugar,riesgo,descripcion;
    ImageView fotoTomada;
    String tituloInci,diaInci,periodoInci,lugarInci,riesgoInci,descripcionInci;
    private ProgressDialog progressDialog;
    private DatabaseReference myrefIncidencia;
    private StorageReference mStorageRef;
    private Bitmap imageBitmap = null;



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
        fotoTomada=(ImageView) findViewById(R.id.imageViewMostrarFotoInfo);
        myrefIncidencia= FirebaseDatabase.getInstance().getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference();

        progressDialog=new ProgressDialog(this);


    }

    public void enviarDatosIncidencia(View v){
        tituloInci=titulo.getText().toString();
        diaInci=dia.getText().toString();
        periodoInci=periodo.getText().toString();
        lugarInci=lugar.getText().toString();
        riesgoInci=riesgo.getText().toString();
        descripcionInci=descripcion.getText().toString();
        registrarIncidencia(tituloInci,diaInci,periodoInci,lugarInci,riesgoInci,descripcionInci);


    }
    public void tomarFoto(View v) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    public void subirFoto() {

        String timeStamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());


        // Creamos una referencia a la carpeta y el nombre de la imagen donde se guardara
        StorageReference imagenRef = mStorageRef.child("camara/" + timeStamp + ".jpg");

        //Pasamos la imagen a un array de byte
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] datas = baos.toByteArray();

        // Empezamos con la subida a Firebase
        UploadTask uploadTask = imagenRef.putBytes(datas);
        uploadTask.addOnFailureListener(new OnFailureListener() {

            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(getBaseContext(), "Hubo un error", Toast.LENGTH_LONG);
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getBaseContext(), "Subida con exito", Toast.LENGTH_LONG);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            fotoTomada.setImageBitmap(imageBitmap);
        }
    }

    public void registrarIncidencia(String tituloInci, String diaInci, String periodoInci, String lugarInci, String riesgoInci, String descripcionInci){

        if (tituloInci.equals("")) {
            Toast.makeText(getBaseContext(), "ERROR:Ingresa el titulo", Toast.LENGTH_LONG).show();
        } else if (diaInci.equals("")) {
            Toast.makeText(getBaseContext(), "ERROR:Ingresa el dia", Toast.LENGTH_LONG).show();
        } else if (periodoInci.equals("")) {
            Toast.makeText(getBaseContext(), "ERROR:Ingresa el periodo", Toast.LENGTH_LONG).show();
        } else if (lugarInci.equals("")) {
            Toast.makeText(getBaseContext(), "ERROR:Ingresa el lugar", Toast.LENGTH_LONG).show();
        }else if (riesgoInci.equals("")) {
            Toast.makeText(getBaseContext(), "ERROR:Ingresa el riesgo", Toast.LENGTH_LONG).show();
        }else if (descripcionInci.equals("")) {
            Toast.makeText(getBaseContext(), "ERROR:Ingresa la descripciòn", Toast.LENGTH_LONG).show();
        }else{


            DatabaseReference usersRef = myrefIncidencia.child("Incidencia");
            usersRef.push().setValue(new Incidencia(tituloInci,diaInci,periodoInci,lugarInci,riesgoInci,descripcionInci));
            subirFoto();

            Toast.makeText(getBaseContext(), "Incidencia registrada correctamente", Toast.LENGTH_LONG).show();

           startActivity(new Intent(getApplicationContext(),PrincipalIncidencias.class));


        }


    }
}
