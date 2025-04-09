package com.idat.agenda;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuPrincipal extends AppCompatActivity {

    Button AgregarNotas, ListarNotas, Importantes, Contactos, AcercaDe, CerrarSesion, EstadoCuentaPrincipal;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    ImageView Imagen_usuario;

    TextView UidPrincipal ,NombresPrincipal, CorreoPrincipal;
    ProgressBar progressBar, progressBarDatos;
    ProgressDialog progressDialog;
    LinearLayoutCompat Linear_Nombres, Linear_Correo, Linear_Verificacion;
    DatabaseReference Usuarios;
    Dialog dialog_cuenta_verificada, dialog_informacion, dialog_fecha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Agenda");

        //Imagen_usuario = findViewById(R.id.Imagen_usuario);
        //UidPrincipal = findViewById(R.id.UidPrincipal);
        NombresPrincipal = findViewById(R.id.NombresPrincipal);
        CorreoPrincipal = findViewById(R.id.CorreoPrincipal);
        //EstadoCuentaPrincipal = findViewById(R.id.EstadoCuentaPrincipal);
        progressBar = findViewById(R.id.progress_BarDatos);

        Usuarios = FirebaseDatabase.getInstance().getReference("Usuarios");
        CerrarSesion = findViewById(R.id.CerrarSesion);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        CerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SalirAplicacion();
            }
        });

    }

    @Override
    protected void onStart() {
        ComprobarInicioSesion();
        super.onStart();
    }

    private void ComprobarInicioSesion() {
        if (user != null) {
            CargaDeDatos();
        } else {
            startActivity(new Intent(MenuPrincipal.this, MainActivity.class));
            finish();
        }
    }

    private void CargaDeDatos() {
        Usuarios.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot ) {

                if (snapshot.exists()){
                    progressBar.setVisibility(View.GONE);

                    NombresPrincipal.setVisibility(View.VISIBLE);
                    CorreoPrincipal.setVisibility(View.VISIBLE);

                    String nombres = "" + snapshot.child("nombres").getValue();
                    String correo = "" + snapshot.child("correo").getValue();

                    NombresPrincipal.setText(nombres);
                    CorreoPrincipal.setText(correo);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void SalirAplicacion() {
        firebaseAuth.signOut();
        startActivity(new Intent(MenuPrincipal.this, MainActivity.class));
        Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show();
    }
}