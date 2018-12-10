package com.example.carlosmedina.control.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carlosmedina.control.Adapters.AdaptadorEstudiante;
import com.example.carlosmedina.control.Adapters.AdaptadorFireBase;
import com.example.carlosmedina.control.Adapters.AdaptadorInicio;
import com.example.carlosmedina.control.DataBase.daoEstudiante;
import com.example.carlosmedina.control.DataBase.daoMensaje;
import com.example.carlosmedina.control.MainActivity;
import com.example.carlosmedina.control.Model.Estudiante;
import com.example.carlosmedina.control.Model.Mensaje;
import com.example.carlosmedina.control.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ControllerInicio extends AppCompatActivity {
    daoMensaje daoMsj;
    AdaptadorInicio  adaptadorInicio;
    Mensaje msj;
    Activity activity;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(); //Conexión a DB firebase
    DatabaseReference childMensaje = databaseReference.child("mensaje");
    DatabaseReference childTitulo = databaseReference.child("titulo");
    private TextView mensaje;
    private TextView titulo;
    AdaptadorFireBase firebase;

    daoEstudiante daoEst;
    AdaptadorEstudiante adaptadorEst;
    ArrayList<Estudiante> lstEstudiante;
    Estudiante e;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        daoEst = new daoEstudiante(this);

        activity = this;

        //Referencia a los elementos del activity
        Button btnGrupos = (Button) findViewById(R.id.btnGrupos);
        Button btnEstudiantes = (Button) findViewById(R.id.btnEstudiantes);
        mensaje = (TextView) findViewById(R.id.mensaje);
        titulo = (TextView) findViewById(R.id.titulo);
        Button sincronizar = (Button) findViewById(R.id.btnSincronizar);


        btnGrupos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, ControllerGrupo.class );
                startActivity(i);
            }
        });
        btnEstudiantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, ControllerLstGeneralEst.class );
                startActivity(i);
            }
        });

        sincronizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sincronizarConFireBase();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        childMensaje.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String texto = dataSnapshot.getValue().toString();
                mensaje.setText(texto);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        childTitulo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String texto = dataSnapshot.getValue().toString();
                titulo.setText(texto);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void sincronizarConFireBase() {
        lstEstudiante = daoEst.getLstEstudiantes();
        if(isOnlineNet()){
            try{
                if(lstEstudiante == null || lstEstudiante.size() == 0)
                {
                    Toast.makeText(activity, "No existen datos para sincronizar", Toast.LENGTH_SHORT).show();
                }
                else{
                    for (Estudiante i: lstEstudiante){
                        Map<String, Object> datosUsuarios = new HashMap<>();
                        datosUsuarios.put("ID", i.getId());
                        datosUsuarios.put("CEDULA", i.getCedula());
                        datosUsuarios.put("NOMBRES", i.getNombre());
                        datosUsuarios.put("APELLIDOS", i.getApellido());
                        datosUsuarios.put("CELULAR", i.getCelular());
                        datosUsuarios.put("TEL_FIJO", i.getTelFijo());
                        datosUsuarios.put("EMAIL", i.getEmail());
                        datosUsuarios.put("GRUPO", i.getGrupo());
                        datosUsuarios.put("CATEGORIA", i.getCategoria());
                        datosUsuarios.put("FECHA_INCRIPCION", i.getFechaDeInscripcion());
                        datosUsuarios.put("FECHA_NACIMIENTO", i.getFechaDeNacimiento());
                        datosUsuarios.put("NUM_FALTAS", i.getFaltas());
                        //Información acudiente
                        datosUsuarios.put("ACUDIENTE", i.getNombreAcudiente());
                        datosUsuarios.put("TEL_ACUDIENTE", i.getTelAcudiente());
                        //Información contable:
                        datosUsuarios.put("ESTADO_PAGO", i.getPago());
                        datosUsuarios.put("FECHA_ULTIMO_PAGO", i.getFechaUltimoPago());
                        datosUsuarios.put("FECHA_VENCIMIENTO_PAGO", i.getFechaVencimientoPago());
                        databaseReference.child("USUARIOS").child(Integer.toString(i.getCedula())).setValue(datosUsuarios);
                        Toast.makeText(activity, "Sincronización exitosa", Toast.LENGTH_SHORT).show();
                    }
                }
            }catch (Exception e){
                Toast.makeText(activity, "Error al sincronizar los datos con el servidor" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(activity, "No hay conexión a la red", Toast.LENGTH_SHORT).show();
        }

    }

    public Boolean isOnlineNet() {

        try {
            Process p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.es");

            int val           = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
