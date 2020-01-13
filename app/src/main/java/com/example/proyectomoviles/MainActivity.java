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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    Button btnRegistro,btnEntrar;
    EditText emailEntrar,contrasenaEntrar;
    private String emailEntrarfb;
    private String contrasEntrarfb;
    private FirebaseAuth mLogin;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLogin=FirebaseAuth.getInstance();

        btnRegistro=(Button) findViewById(R.id.btnRegistro);
        btnEntrar=(Button) findViewById(R.id.btnEntrar);
        emailEntrar=(EditText) findViewById(R.id.editTextEmailEntrar);
        contrasenaEntrar=(EditText) findViewById(R.id.editTextContrasenaEntrar);
        progressDialog=new ProgressDialog(this);


        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent registro=new Intent(view.getContext(),RegistroUsuarios.class);
                startActivityForResult(registro,0);
            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                emailEntrarfb=emailEntrar.getText().toString();
                contrasEntrarfb=contrasenaEntrar.getText().toString();
                if(!emailEntrarfb.isEmpty() && !contrasEntrarfb.isEmpty()){

                    loginUsuario();
                }
                else{
                    Toast.makeText(getBaseContext(), "ERROR:Campos incompletos", Toast.LENGTH_LONG).show();

                }





            }
        });


    }

    private  void loginUsuario(){

        progressDialog.setMessage("Ingresando a la cuenta");
        progressDialog.show();

        mLogin.signInWithEmailAndPassword(emailEntrarfb,contrasEntrarfb).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    startActivity(new Intent(getApplicationContext(),PrincipalIncidencias.class));
                }
                else {
                    Toast.makeText(getBaseContext(), "ERROR:Datos incorrectos", Toast.LENGTH_LONG).show();

                }

                progressDialog.dismiss();

            }
        });

    }
}
