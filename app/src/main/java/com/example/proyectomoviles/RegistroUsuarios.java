package com.example.proyectomoviles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectomoviles.domain.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroUsuarios extends AppCompatActivity {


    EditText nombreUser,apellidosUser,emailUser,matriculaUser,contrasenaUser;
    Button btnRegistroUserfire;
    String varNombre,varApellido,varEmail,varMatricula,varContrasena;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);

        nombreUser=(EditText) findViewById(R.id.editTextNombre);
        apellidosUser=(EditText) findViewById(R.id.editTextApellido);
        emailUser=(EditText) findViewById(R.id.editTextEmailEntrar);
        matriculaUser=(EditText) findViewById(R.id.editTextMatricula);
        contrasenaUser=(EditText) findViewById(R.id.editTextContrasenaEntrar);
        btnRegistroUserfire=(Button) findViewById(R.id.btnRegistrofb);
        mAuth=FirebaseAuth.getInstance();
        mDatabase=FirebaseDatabase.getInstance().getReference();

        progressDialog=new ProgressDialog(this);

        btnRegistroUserfire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


enviarDatos();

            }
        });
    }

    public void enviarDatos(){
        varNombre=nombreUser.getText().toString();
        varApellido=apellidosUser.getText().toString();
        varEmail=emailUser.getText().toString();
        varMatricula=matriculaUser.getText().toString();
        varContrasena=contrasenaUser.getText().toString();

        registrarUsuario(varNombre,varApellido,varEmail,varMatricula,varContrasena);

    }

    public void registrarUsuario(final String varNombre, final String varApellido, final String varEmail, final String varMatricula, final String varContrasena) {

        if (varNombre.equals("")) {
            Toast.makeText(getBaseContext(), "ERROR:Ingresa el nombre", Toast.LENGTH_LONG).show();
        } else if (varApellido.equals("")) {
            Toast.makeText(getBaseContext(), "ERROR:Ingresa tus apellidos", Toast.LENGTH_LONG).show();
        } else if (varEmail.equals("")) {
            Toast.makeText(getBaseContext(), "ERROR:Ingresa tu email", Toast.LENGTH_LONG).show();
        } else if (varMatricula.equals("")) {
            Toast.makeText(getBaseContext(), "ERROR:Ingresa tu matricula", Toast.LENGTH_LONG).show();
        } else if(varContrasena.length()<6){
            Toast.makeText(getBaseContext(), "ERROR:Ingresa uana contraseña con almenos 6 caracteres", Toast.LENGTH_LONG).show();
        }
          else if (varContrasena.equals("")) {
            Toast.makeText(getBaseContext(), "ERROR:Ingresa la contraseña", Toast.LENGTH_LONG).show();
        }
          else{
            progressDialog.setMessage("Realizando registro");
            progressDialog.show();
            mAuth.createUserWithEmailAndPassword(varEmail,varContrasena).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){

                        String id=mAuth.getCurrentUser().getUid();
                        mDatabase.child("Users").child(id).push().setValue(new Usuario(varNombre,varApellido,varEmail,varMatricula,varContrasena));
                        Toast.makeText(getBaseContext(), "Registro correcto del correo "+emailUser.getText(), Toast.LENGTH_LONG).show();

                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();

                    }
                    else{
                        Toast.makeText(getBaseContext(), "ERROR:No se puedo regsitar", Toast.LENGTH_LONG).show();
                    }

                    progressDialog.dismiss();

                }
            });

        }
    }
}
