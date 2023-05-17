package com.example.agendatuuduu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuPrincipal extends AppCompatActivity {

    Button CerrarSesion;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    TextView NombresPrincipal, CorreoPrincipal;
    ProgressBar progressBarDatos;

    DatabaseReference Usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);


        NombresPrincipal =findViewById(R.id.NombresPrincipal);
        CorreoPrincipal = findViewById(R.id.CorrePrincipal);
        progressBarDatos = findViewById(R.id.progressBarDatos);
        Usuarios = FirebaseDatabase.getInstance().getReference("Usuarios");


        CerrarSesion = findViewById(R.id.CerrarSesion);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        CerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SalirApp();
            }
        });
    }

    @Override
    protected void onStart() {
        ComprobarInicioSesion();
        super.onStart();
    }


    private void ComprobarInicioSesion(){
        if (user!=null){
            CargaDeDatos();
        }else {
            startActivity(new Intent(MenuPrincipal.this,MainActivity.class));
            finish();
        }
    }


    private void CargaDeDatos(){
        Usuarios.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                   progressBarDatos.setVisibility(View.GONE);

                   NombresPrincipal.setVisibility(View.VISIBLE);
                   CorreoPrincipal.setVisibility(View.VISIBLE);

                   String nombres = ""+snapshot.child("nombres").getValue();
                   String correo = ""+snapshot.child("correo").getValue();

                   NombresPrincipal.setText(nombres);
                   CorreoPrincipal.setText(correo);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void SalirApp() {
        firebaseAuth.signOut();
        startActivity(new Intent(MenuPrincipal.this, MainActivity.class));
        Toast.makeText(this, "Cerraste sesion exitosamente",Toast.LENGTH_SHORT).show();
    }
}