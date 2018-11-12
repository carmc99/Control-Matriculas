package com.example.carlosmedina.control.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carlosmedina.control.DataBase.daoEstudiante;
import com.example.carlosmedina.control.Model.Estudiante;
import com.example.carlosmedina.control.R;

import java.util.ArrayList;

public class AdaptadorEstudiante extends BaseAdapter {
    ArrayList<Estudiante> lstEstudiante;
    daoEstudiante daoEst;
    Estudiante est;
    Activity activity;
    int id= 0; //referencia a cada fila

    public AdaptadorEstudiante(Activity activity, ArrayList<Estudiante> lstEstudiantes, daoEstudiante daoEst) {
        this.lstEstudiante = lstEstudiantes;
        this.activity = activity;
        this.daoEst = daoEst;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getCount() {
        return lstEstudiante.size();
    }

    @Override
    public Estudiante getItem(int position) {  //Corregir
        est = lstEstudiante.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        est = lstEstudiante.get(position);
        return est.getCedula();
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View v = convertView;
        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.listview_row, null);
        }
        est = lstEstudiante.get(position);
        Button btnEditar = (Button) v.findViewById(R.id.editar1);
        Button btnEliminar = (Button) v.findViewById(R.id.elim1);
        TextView columna1 = (TextView) v.findViewById(R.id.nombre);
        TextView columna2 = (TextView) v.findViewById(R.id.faltas);
        TextView columna3 = (TextView) v.findViewById(R.id.pago);


        //Referencia a la numero de el boton en la lista.
        btnEditar.setTag(position);
        btnEliminar.setTag(position);

        //Rellenamos los valores de cada columna de la fila
        columna1.setText(est.getNombre() + " " + est.getApellido());
        //columna2.setText(est.getApellido());
        //columna3.setText(est.getNombre());

        //Boton editar
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Abrir formulario para crear estudiante
                int pos = Integer.parseInt(v.getTag().toString());
                final Dialog dialog = new Dialog(activity, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
                dialog.setTitle("Editar registro");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.activity_crear_estudiante);
                dialog.show();
                final EditText cedula = (EditText) dialog.findViewById(R.id.cedula);
                final EditText nombre = (EditText) dialog.findViewById(R.id.nombres);
                final EditText apellidos = (EditText) dialog.findViewById(R.id.apellidos);
                final EditText celular = (EditText) dialog.findViewById(R.id.celular);
                final EditText fijo = (EditText) dialog.findViewById(R.id.fijo);
                final EditText email = (EditText) dialog.findViewById(R.id.email);
                Button btnGuardar = (Button) dialog.findViewById(R.id.btnCrearEstudiante);
                Button btnCancelar = (Button) dialog.findViewById(R.id.btnCancelar);
                est = lstEstudiante.get(pos);
                setId(est.getId());
                //Setear los campos del formulario
                cedula.setText((Integer.toString(est.getCedula())));
                nombre.setText(est.getNombre());
                apellidos.setText(est.getApellido());
                celular.setText(est.getCelular());
                fijo.setText(est.getTelFijo());

                btnGuardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Crear un nuevo estudiante
                        try {

                            est = new Estudiante(getId(),
                                    Integer.parseInt(cedula.getText().toString()),
                                    nombre.getText().toString(),
                                    apellidos.getText().toString(),
                                    celular.getText().toString(),
                                    fijo.getText().toString()
                                    //email.getText().toString()
                            );
                            daoEst.editar(est);
                            lstEstudiante = daoEst.getLstEstudiantes();
                            notifyDataSetChanged();
                            dialog.dismiss();
                        } catch (Exception e) {
                            Toast.makeText(activity, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

        //Boton eliminar
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(v.getTag().toString());
                est = lstEstudiante.get(pos);
                AlertDialog.Builder confirma = new AlertDialog.Builder(activity);
                confirma.setMessage("¿Estas seguro querer eliminar este estudiante?");
                confirma.setCancelable(false);

                confirma.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                confirma.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        daoEst.eliminar(est.getId());
                        lstEstudiante = daoEst.getLstEstudiantes();
                        notifyDataSetChanged();
                    }
                });

                confirma.show();
            }
        });

        return v;
    }
}
