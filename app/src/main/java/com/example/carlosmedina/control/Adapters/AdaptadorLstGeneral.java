package com.example.carlosmedina.control.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.carlosmedina.control.DataBase.daoEstudiante;
import com.example.carlosmedina.control.Model.Estudiante;
import com.example.carlosmedina.control.R;

import java.util.ArrayList;

public class AdaptadorLstGeneral extends BaseAdapter {
    ArrayList<Estudiante> lstEstudiante;
    daoEstudiante daoEst;
    Estudiante est;
    Activity activity;
    int id= 0; //referencia a cada fila

    public AdaptadorLstGeneral(Activity activity, ArrayList<Estudiante> lstEstudiantes, daoEstudiante daoEst) {
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
        return est.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.listview_row_general, null);
        }
        est = lstEstudiante.get(position);
        TextView columna1 = (TextView) v.findViewById(R.id.nombre);
        TextView columna3 = (TextView) v.findViewById(R.id.grupo);
        //Referencia a la numero de el boton en la lista.
        //Rellenamos los valores de cada columna de la fila
        columna1.setText(est.getNombre() + " " + est.getApellido());
        //columna2.setText(est.getApellido());
        columna3.setText(Integer.toString(est.getGrupo()));

        return v;
    }
}
