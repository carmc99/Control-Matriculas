package com.example.carlosmedina.control.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.carlosmedina.control.DataBase.daoMensaje;
import com.example.carlosmedina.control.Model.Mensaje;
import com.example.carlosmedina.control.R;

public class AdaptadorInicio extends BaseAdapter {
    daoMensaje daoMsj;
    Mensaje msj;
    Activity activity;

    public AdaptadorInicio(Activity activity, Mensaje msj, daoMensaje daoMsj) {
        this.msj = msj;
        this.activity = activity;
        this.daoMsj = daoMsj;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.activity_principal, null);
        }

        TextView columna1 = (TextView) v.findViewById(R.id.mensaje);
        Button btnLstGrupos = (Button) v.findViewById(R.id.btnGrupos);
        Button btnLstEstudiantes = (Button) v.findViewById(R.id.btnEstudiantes);
        // ImageView columna3 = (ImageView) v.findViewById(R.id.listview_image);


        columna1.setText(msj.getMensaje());


        return v;
    }
}
