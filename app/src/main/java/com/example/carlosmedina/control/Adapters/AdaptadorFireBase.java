package com.example.carlosmedina.control.Adapters;

import com.example.carlosmedina.control.DataBase.daoEstudiante;
import com.example.carlosmedina.control.Model.Estudiante;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdaptadorFireBase {
    daoEstudiante daoEst;
    ArrayList<Estudiante> lstEstudiante;
    Estudiante e;
    DatabaseReference databaseReference;

    public AdaptadorFireBase() {

    }

    public void sincronizarDatos(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
        lstEstudiante = daoEst.getLstEstudiantes();
        Map<String, Object> datosUsuarios = new HashMap<>();

        //for (Estudiante e : lstEstudiante){
            datosUsuarios.put("nombre", "gg");
            datosUsuarios.put("cedula", "jj");
       // }
        databaseReference.child("usuario").push().setValue(datosUsuarios);
    }
}
