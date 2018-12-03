package com.example.carlosmedina.control.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carlosmedina.control.DataBase.daoGrupo;
import com.example.carlosmedina.control.Model.Grupo;
import com.example.carlosmedina.control.R;

import java.util.ArrayList;

public class AdaptadorGrupo extends BaseAdapter {
    ArrayList<Grupo> lstGrupos;
    daoGrupo daoGroup;
    Grupo g;
    Activity activity;
    int id= 0;

    public AdaptadorGrupo(Activity activity, ArrayList<Grupo> lstGrupos, daoGrupo daoGroup) {
        this.lstGrupos = lstGrupos;
        this.activity = activity;
        this.daoGroup = daoGroup;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getCount() {
        return lstGrupos.size();
    }

    @Override
    public Object getItem(int position) {
        g = lstGrupos.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        g = lstGrupos.get(position);
        return g.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.listview_row_grupos, null);
        }
        g = lstGrupos.get(position);

        //Referencia  a los elementos de la vista
        TextView columna1 = (TextView) v.findViewById(R.id.titulo);
        TextView columna2 = (TextView) v.findViewById(R.id.descripcion);
       // ImageView columna3 = (ImageView) v.findViewById(R.id.listview_image);
        Button btnEditarGrupo = (Button) v.findViewById(R.id.btnEditarGrupo);
        Button btnEliminarGrupo = (Button) v.findViewById(R.id.btnEliminarGrupo);
        btnEditarGrupo.setTag(position);
        btnEliminarGrupo.setTag(position);

        columna1.setText(g.getNombre().toUpperCase());
        columna2.setText(g.getDescripcion());

        //Boton editar grupo
        btnEditarGrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Abrir formulario para crear estudiante
                int pos = Integer.parseInt(v.getTag().toString());
                final Dialog dialog = new Dialog(activity, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
                dialog.setTitle("Editar registro");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.activity_crear_grupo);
                dialog.show();
                final EditText nombre = (EditText) dialog.findViewById(R.id.data_nombre);
                final EditText descripcion = (EditText) dialog.findViewById(R.id.data_descripcion);
                Button btnCrearGrupo = (Button) dialog.findViewById(R.id.btnCrearGrupo);
                Button btnCancelar = (Button) dialog.findViewById(R.id.btnCancelar);
                g = lstGrupos.get(pos);
                setId(g.getId());
                //Setea los campos del formulario
                nombre.setText(g.getNombre());
                descripcion.setText(g.getDescripcion());

                btnCrearGrupo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // actualiza el grupo
                        try {

                            g = new Grupo(getId(),
                                    nombre.getText().toString(),
                                    descripcion.getText().toString()
                            );
                            daoGroup.editar(g);
                            lstGrupos = daoGroup.getLstGrupos();
                            notifyDataSetChanged();
                            dialog.dismiss();
                            Toast.makeText(activity, "Registro actualizado", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(activity, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                btnCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


            }
        });



        //Boton eliminar grupo
        btnEliminarGrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(v.getTag().toString());
                g = lstGrupos.get(pos);
                AlertDialog.Builder confirma = new AlertDialog.Builder(activity);
                confirma.setMessage("¿Esta seguro de querer eliminar este grupo?");
                confirma.setCancelable(false);

                confirma.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                confirma.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        daoGroup.eliminar(g.getId());
                        lstGrupos = daoGroup.getLstGrupos();
                        notifyDataSetChanged();
                        Toast.makeText(activity, "Registro eliminado", Toast.LENGTH_SHORT).show();
                    }
                });

                confirma.show();

            }
        });

        return v;
    }

}
