package com.example.carlosmedina.control;
/**
 * Created by Carlos Medina C.
 */


import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.carlosmedina.control.Adapters.AdaptadorEstudiante;
import com.example.carlosmedina.control.Controller.ControllerDetalleEst;
import com.example.carlosmedina.control.DataBase.daoEstudiante;
import com.example.carlosmedina.control.Model.Estudiante;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    daoEstudiante daoEst;
    AdaptadorEstudiante adaptadorEst;
    ArrayList<Estudiante> lstEstudiante;
    Estudiante e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_estudiantes2);
        daoEst = new daoEstudiante(this);
        lstEstudiante = daoEst.getLstEstudiantes();

        //Referencia a los elementos del activity

        Button añadirEst = (Button) findViewById(R.id.btnAddEstudiante);



        //Dialogo formulario crear nuevo estudiante
        final Dialog dialogo;
        View aux = getLayoutInflater().inflate(R.layout.activity_crear_estudiante, null);
        dialogo = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        dialogo.setContentView(aux);

        adaptadorEst = new AdaptadorEstudiante(this, lstEstudiante, daoEst);
        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adaptadorEst);



        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Ver detalle estudiante
                final Dialog dialog;
                View aux1 = getLayoutInflater().inflate(R.layout.activity_detalle_estudiante, null);
                dialog = new Dialog(getApplication(), android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                dialog.setContentView(aux1);
                dialog.show();
                //Terminar...........
            }
        });
        añadirEst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Abrir formulario para crear estudiante
                dialogo.show();
                final EditText cedula = (EditText) dialogo.findViewById(R.id.cedula);
                final EditText nombre = (EditText) dialogo.findViewById(R.id.nombres);
                final EditText apellidos = (EditText) dialogo.findViewById(R.id.apellidos);
                final EditText celular = (EditText) dialogo.findViewById(R.id.celular);
                final EditText fijo = (EditText) dialogo.findViewById(R.id.fijo);
                final EditText email = (EditText) dialogo.findViewById(R.id.email);
                final EditText pago = (EditText) dialogo.findViewById(R.id.pago);
                Button btnGuardar = (Button) dialogo.findViewById(R.id.btnCrearEstudiante);
                Button btnCancelar = (Button) dialogo.findViewById(R.id.btnCancelar);
                btnGuardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Crear un nuevo estudiante
                        try {

                            e = new Estudiante(
                                    Integer.parseInt(cedula.getText().toString()),
                                    nombre.getText().toString(),
                                    apellidos.getText().toString(),
                                    celular.getText().toString(),
                                    fijo.getText().toString(),
                                    email.getText().toString(),
                                    pago.getText().toString()
                            );
                            daoEst.insertar(e);
                            lstEstudiante = daoEst.getLstEstudiantes();
                            adaptadorEst.notifyDataSetChanged();
                            dialogo.dismiss();
                        } catch (Exception e) {
                            Toast.makeText(getApplication(), "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                btnCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Retorna a la vista anterior
                        dialogo.dismiss();
                    }
                });

            }
        });
    }
}
