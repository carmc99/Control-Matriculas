package com.example.carlosmedina.control;
/**
 * Created by Carlos Medina C.
 */


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.carlosmedina.control.Adapters.AdaptadorEstudiante;
import com.example.carlosmedina.control.Adapters.AdaptadorFireBase;
import com.example.carlosmedina.control.DataBase.daoEstudiante;
import com.example.carlosmedina.control.Model.Estudiante;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    daoEstudiante daoEst;
    AdaptadorEstudiante adaptadorEst;
    ArrayList<Estudiante> lstEstudiante;
    Estudiante e;
    int grupoId;
    Activity activity;
    Tool f;
    private String fechaUltimoPago;
    private String fechaVencimientoPago;
    private boolean pagado = false;
    private String fechaNacimiento;

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

        añadirEst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Abrir formulario para crear estudiante
                dialogo.show();
                //RadioButton radioButtonMasculino= (RadioButton) findViewById(R.id.male);
                final RadioButton radioButtonFemenino = (RadioButton) findViewById(R.id.female);
                final EditText cedula = (EditText) dialogo.findViewById(R.id.cedula);
                final EditText nombre = (EditText) dialogo.findViewById(R.id.nombres);
                final EditText apellidos = (EditText) dialogo.findViewById(R.id.apellidos);
                final EditText celular = (EditText) dialogo.findViewById(R.id.celular);
                final EditText fijo = (EditText) dialogo.findViewById(R.id.fijo);
                final EditText email = (EditText) dialogo.findViewById(R.id.email);
                final EditText pago = (EditText) dialogo.findViewById(R.id.pago);
                final EditText nombreAcudiente = (EditText) dialogo.findViewById(R.id.acudiente);
                final EditText telAcudiente = (EditText) dialogo.findViewById(R.id.celular_acudiente);
                final EditText categoria = (EditText) dialogo.findViewById(R.id.categoria);
                /*final EditText fechaNacimiento = (EditText) dialogo.findViewById(R.id.pago);
                final EditText numFaltas = (EditText) dialogo.findViewById(R.id.pago);*/
                final String genero = "gg";
                final int numFaltas = 1;


                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Calendar calendar = Calendar.getInstance();
                final String fechaInscripcion = simpleDateFormat.format(calendar.getTime());
                //FECHA_VENCIMIENTO_PAGO
                ImageButton btnFechaNacimiento = (ImageButton) findViewById(R.id.ib_obtener_fecha);
                Button btnPagar = (Button) dialogo.findViewById(R.id.btnPagar);
                Button btnGuardar = (Button) dialogo.findViewById(R.id.btnCrearEstudiante);
                Button btnCancelar = (Button) dialogo.findViewById(R.id.btnCancelar);
                btnGuardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(pagado) {
                            // Crear un nuevo estudiante
                            try {
                                e = new Estudiante(
                                        Integer.parseInt(cedula.getText().toString()),
                                        nombre.getText().toString(),
                                        apellidos.getText().toString(),
                                        celular.getText().toString(),
                                        fijo.getText().toString(),
                                        email.getText().toString(),
                                        genero,
                                        pago.getText().toString(),
                                        grupoId,
                                        categoria.getText().toString(),
                                        fechaNacimiento,
                                        nombreAcudiente.getText().toString(),
                                        telAcudiente.getText().toString(),
                                        fechaInscripcion,
                                        fechaUltimoPago,
                                        fechaVencimientoPago,
                                        numFaltas
                                );
                                daoEst.insertar(e);
                                lstEstudiante = daoEst.getLstGeneralEstudiantes(grupoId);
                                adaptadorEst.notifyDataSetChanged();
                                dialogo.dismiss();
                                Toast.makeText(activity, "Registro exitoso", Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                Toast.makeText(getApplication(), "Ha ocurrido un error al insertar el registro", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(activity, "Aún no se ha registrado un pago", Toast.LENGTH_SHORT).show();
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
                btnPagar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Calendar calendar = Calendar.getInstance();
                        fechaUltimoPago = simpleDateFormat.format(calendar.getTime());
                        calendar.setTime(new Date());
                        int calendarTime = Calendar.DAY_OF_MONTH;
                        int temp = calendar.get(calendarTime);
                        calendar.set(calendarTime, temp+30);
                        Date newDate = calendar.getTime();
                        fechaVencimientoPago = simpleDateFormat.format(newDate);
                        pagado = true;
                    }
                });

            }
        });


    }
}

