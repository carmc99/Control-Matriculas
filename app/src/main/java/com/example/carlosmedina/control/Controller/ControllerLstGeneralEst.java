package com.example.carlosmedina.control.Controller;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carlosmedina.control.Adapters.AdaptadorEstudiante;
import com.example.carlosmedina.control.Adapters.AdaptadorLstGeneral;
import com.example.carlosmedina.control.DataBase.daoEstudiante;
import com.example.carlosmedina.control.Model.Estudiante;
import com.example.carlosmedina.control.R;

import java.util.ArrayList;

public class ControllerLstGeneralEst extends AppCompatActivity {
    daoEstudiante daoEst;
    AdaptadorLstGeneral adaptadorLstGeneral;
    ArrayList<Estudiante> lstEstudiante;
    Estudiante e;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_general_estudiantes);
        daoEst = new daoEstudiante(this);
        lstEstudiante = daoEst.getLstEstudiantes();
        activity = this;

        Button btnVolver = (Button) findViewById(R.id.btnVolver);
        adaptadorLstGeneral = new AdaptadorLstGeneral(this, lstEstudiante, daoEst);
        ListView listview = (ListView) findViewById(R.id.listview_general);
        listview.setAdapter(adaptadorLstGeneral);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
                    final TextView fechaIncripcion = (TextView) dialog.findViewById(R.id.data_fechaInscripcion);
                    final TextView nombre = (TextView) dialog.findViewById(R.id.titulo_inicio);
                    final TextView acudiente = (TextView) dialog.findViewById(R.id.data_acudiente);
                    final TextView celular = (TextView) dialog.findViewById(R.id.data_celular);
                    final TextView fijo = (TextView) dialog.findViewById(R.id.data_tel_fijo);
                    final TextView email = (TextView) dialog.findViewById(R.id.data_email);
                    final TextView pago = (TextView) dialog.findViewById(R.id.data_pago);
                    final TextView categoria = (TextView) dialog.findViewById(R.id.data_categoria);
                    final TextView genero = (TextView) dialog.findViewById(R.id.data_genero);
                    final TextView fechaNacimiento = (TextView) dialog.findViewById(R.id.data_edad);
                    final TextView telAcudiente = (TextView) dialog.findViewById(R.id.data_tel_acudiente);
                    final TextView numFaltas = (TextView) dialog.findViewById(R.id.data_faltas);
                    final TextView fechaVencimientoPago = (TextView) dialog.findViewById(R.id.data_vencimiento);
                    final TextView fechaUltimoPago = (TextView) dialog.findViewById(R.id.data_ultimo_pago);
                    final TextView nombre1 = (TextView) dialog.findViewById(R.id.data_nombre1);
                    final TextView apellido1= (TextView) dialog.findViewById(R.id.data_apellido1);

                    e = lstEstudiante.get(position);
                    nombre1.setText(e.getNombre());
                    apellido1.setText(e.getApellido());
                    fechaIncripcion.setText("Inscripción: " + e.getFechaDeInscripcion());
                    nombre.setText(e.getNombre());
                    cedula.setText((Integer.toString(e.getCedula())));
                    acudiente.setText(e.getNombreAcudiente());
                    celular.setText(e.getCelular());
                    fijo.setText(e.getTelFijo());
                    email.setText(e.getEmail());
                    pago.setText(e.getPago());
                    genero.setText(e.getGenero());
                    categoria.setText(e.getCategoria());
                    telAcudiente.setText(e.getTelAcudiente());
                    fechaNacimiento.setText(e.getFechaDeNacimiento());
                    numFaltas.setText((Integer.toString(e.getFaltas())));
                    fechaVencimientoPago.setText(e.getFechaVencimientoPago());
                    fechaUltimoPago.setText(e.getFechaUltimoPago());
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
    }
}
