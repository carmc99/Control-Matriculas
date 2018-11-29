package com.example.carlosmedina.control.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.carlosmedina.control.Adapters.AdaptadorInicio;
import com.example.carlosmedina.control.DataBase.daoMensaje;
import com.example.carlosmedina.control.MainActivity;
import com.example.carlosmedina.control.Model.Mensaje;
import com.example.carlosmedina.control.R;

public class ControllerInicio extends AppCompatActivity {
    daoMensaje daoMsj;
    AdaptadorInicio  adaptadorInicio;
    Mensaje msj;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        activity = this;
        //Referencia a los elementos del activity
        Button btnGrupos = (Button) findViewById(R.id.btnGrupos);
        Button btnEstudiantes = (Button) findViewById(R.id.btnEstudiantes);
        TextView mensaje = (TextView) findViewById(R.id.mensaje);
        mensaje.setText("\"Mientras perseveramos y resistimos, podemos conseguir todo lo que queremos\"" + "\n" + "- Mike Tyson");

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
    }
}
