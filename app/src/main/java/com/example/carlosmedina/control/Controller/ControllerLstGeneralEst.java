package com.example.carlosmedina.control.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.carlosmedina.control.Adapters.AdaptadorEstudiante;
import com.example.carlosmedina.control.DataBase.daoEstudiante;
import com.example.carlosmedina.control.Model.Estudiante;
import com.example.carlosmedina.control.R;

import java.util.ArrayList;

public class ControllerLstGeneralEst extends AppCompatActivity {
    daoEstudiante daoEst;
    AdaptadorEstudiante adaptadorEst;
    ArrayList<Estudiante> lstEstudiante;
    Estudiante e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_general_estudiantes);
        daoEst = new daoEstudiante(this);
        lstEstudiante = daoEst.getLstEstudiantes();

        Button btnVolver = (Button) findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
