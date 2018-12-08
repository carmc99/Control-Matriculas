package com.example.carlosmedina.control;
/**
 * Created by Carlos Medina C.
 */


import android.app.Activity;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.carlosmedina.control.Adapters.AdaptadorEstudiante;
import com.example.carlosmedina.control.Adapters.AdaptadorFireBase;
import com.example.carlosmedina.control.DataBase.daoEstudiante;
import com.example.carlosmedina.control.Model.Estudiante;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    daoEstudiante daoEst;
    AdaptadorEstudiante adaptadorEst;
    ArrayList<Estudiante> lstEstudiante;
    Estudiante e;
    int grupoId;
    Activity activity;
    Tool f;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_estudiantes2);
        daoEst = new daoEstudiante(this);
        Bundle intentExtras = getIntent().getExtras();
        if (intentExtras != null) {
            grupoId = intentExtras.getInt("grupoId"); //Obtiene los datos enviados desde la vista de grupos
        }
        activity =this;
        lstEstudiante = daoEst.getLstGeneralEstudiantes(grupoId);


        //Referencia a los elementos del activity

        Button añadirEst = (Button) findViewById(R.id.btnAddEstudiante);



        //Dialogo formulario crear nuevo estudiante
        final Dialog dialogo;
        View aux = getLayoutInflater().inflate(R.layout.activity_crear_estudiante, null);
        dialogo = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        dialogo.setContentView(aux);

        adaptadorEst = new AdaptadorEstudiante(this, lstEstudiante, daoEst,grupoId);
        ListView listview = (ListView) findViewById(R.id.listview_general);
        listview.setAdapter(adaptadorEst);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Dialog dialog = new Dialog(activity, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
                dialog.setTitle("Ver registro");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.activity_detalle_estudiante);
                dialog.show();
                Button btnVolver = (Button) dialog.findViewById(R.id.btnVolver);
                try {
                    final TextView cedula = (TextView) dialog.findViewById(R.id.data_cedula);
                    final TextView nombre = (TextView) dialog.findViewById(R.id.titulo_inicio);
                    final TextView acudiente = (TextView) dialog.findViewById(R.id.data_acudiente);
                    final TextView celular = (TextView) dialog.findViewById(R.id.data_celular);
                    final TextView fijo = (TextView) dialog.findViewById(R.id.data_tel_fijo);
                    final TextView email = (TextView) dialog.findViewById(R.id.data_email);

                    final TextView pago = (TextView) dialog.findViewById(R.id.data_pago);


                    e = lstEstudiante.get(position);
                    nombre.setText(e.getNombre());
                    cedula.setText((Integer.toString(e.getCedula())));
                    //acudiente.setText(e.ge);
                    celular.setText(e.getCelular());
                    fijo.setText(e.getTelFijo());
                    email.setText(e.getEmail());
                    pago.setText(e.getPago());
                }catch (Exception e){
                    Toast.makeText(activity, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                btnVolver.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });



        añadirEst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Abrir formulario para crear estudiante
                dialogo.show();
                String[] lista = new String[3];
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
                                    pago.getText().toString(),
                                    grupoId
                            );

                            daoEst.insertar(e);
                            lstEstudiante = daoEst.getLstGeneralEstudiantes(grupoId);
                            adaptadorEst.notifyDataSetChanged();
                            dialogo.dismiss();
                            Toast.makeText(activity, "Registro exitoso", Toast.LENGTH_SHORT).show();
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

