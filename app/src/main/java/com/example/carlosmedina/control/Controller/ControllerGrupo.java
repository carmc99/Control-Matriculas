package com.example.carlosmedina.control.Controller;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.carlosmedina.control.Adapters.AdaptadorEstudiante;
import com.example.carlosmedina.control.Adapters.AdaptadorGrupo;
import com.example.carlosmedina.control.DataBase.daoEstudiante;
import com.example.carlosmedina.control.DataBase.daoGrupo;
import com.example.carlosmedina.control.MainActivity;
import com.example.carlosmedina.control.Model.Estudiante;
import com.example.carlosmedina.control.Model.Grupo;
import com.example.carlosmedina.control.R;

import java.util.ArrayList;

public class ControllerGrupo extends AppCompatActivity {
    daoGrupo daoGroup;
    AdaptadorGrupo adaptadorGrupo;
    ArrayList<Grupo> lstGrupos;
    Grupo g;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_grupos);
        daoGroup = new daoGrupo(this);
        lstGrupos = daoGroup.getLstGrupos();

        Button añadirGrupo = (Button) findViewById(R.id.btnAddGrupo);

        final Dialog dialogo;
        View aux = getLayoutInflater().inflate(R.layout.activity_crear_grupo, null);
        dialogo = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        dialogo.setContentView(aux);


        adaptadorGrupo = new AdaptadorGrupo(this, lstGrupos, daoGroup);
        ListView listview = (ListView) findViewById(R.id.list_view);
        listview.setAdapter(adaptadorGrupo);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplication(), "" + position, Toast.LENGTH_SHORT).show();
                Bundle extras = new Bundle();
                extras.putInt("grupoId", position);
                Intent i = new Intent(getApplication(), MainActivity.class );
                i.putExtras(extras);  //Envia el Id de referencias a Mainactivity, para determinar que estudiantes mostrar
                startActivity(i);
            }
        });

        añadirGrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo.show();
                final EditText nombre = (EditText) dialogo.findViewById(R.id.data_nombre);
                final EditText descripcion = (EditText) dialogo.findViewById(R.id.data_descripcion);
                Button btnGuardar = (Button) dialogo.findViewById(R.id.btnCrearGrupo);
                Button btnCancelar = (Button) dialogo.findViewById(R.id.btnCancelar);

                btnGuardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Crear un nuevo grupo
                        try {

                            g = new Grupo(
                                    nombre.getText().toString(),
                                    descripcion.getText().toString()
                            );
                            daoGroup.insertar(g);
                            lstGrupos = daoGroup.getLstGrupos();
                            adaptadorGrupo.notifyDataSetChanged();

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
