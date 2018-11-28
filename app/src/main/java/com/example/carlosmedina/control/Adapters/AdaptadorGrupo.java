package com.example.carlosmedina.control.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

        TextView columna1 = (TextView) v.findViewById(R.id.titulo);
        TextView columna2 = (TextView) v.findViewById(R.id.descripcion);
       // ImageView columna3 = (ImageView) v.findViewById(R.id.listview_image);


        columna1.setText(g.getNombre());
        columna2.setText(g.getDescripcion());


        return v;
    }
}
