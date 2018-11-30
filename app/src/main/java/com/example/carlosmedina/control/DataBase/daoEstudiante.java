package com.example.carlosmedina.control.DataBase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.carlosmedina.control.Model.Estudiante;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

public class daoEstudiante{
    SQLiteDatabase db;
    ArrayList<Estudiante> lstEstudiantes = new ArrayList<>();
    Estudiante est;
    Context cx;
    String nombreDb = "DbEstudiantes";
    //String tabla = "CREATE TABLE IF NOT EXISTS ESTUDIANTE(ID INTEGER PRIMARY KEY AUTOINCREMENT, CEDULA INTEGER, NOMBRE TEXT, APELLIDO TEXT, CELULAR TEXT, TELFIJO TEXT, EMAIL TEXT, PAGO TEXT)";
    String tabla = "CREATE TABLE IF NOT EXISTS ESTUDIANTE(ID INTEGER PRIMARY KEY AUTOINCREMENT, CEDULA INTEGER, NOMBRE TEXT," +
            "APELLIDO TEXT, CELULAR TEXT, TELFIJO TEXT, EMAIL TEXT, PAGO TEXT, GRUPO INTEGER)";
    String detalleEst = "CREATE TABLE IF NOT EXISTS ESTDETALLE(ID INTEGER PRIMARY KEY AUTOINCREMENT,PAGO TEXT,CATEGORIA TEXT, EPS TEXT)";
    public daoEstudiante(Context cx) {
        this.cx = cx;
        /*String clearDBQuery = "DELETE FROM " + "ESTUDIANTE";
        db.execSQL(clearDBQuery); */
        db = cx.openOrCreateDatabase(nombreDb, Context.MODE_PRIVATE, null);
        db.execSQL(tabla);
        //db.execSQL(detalleEst);


    }

    public boolean insertar(Estudiante e) {
            ContentValues contenedor = new ContentValues();
            contenedor.put("cedula", e.getCedula());
            contenedor.put("pago", e.getPago());
            //contenedor.put("falta", 1);
            contenedor.put("nombre", e.getNombre());
            contenedor.put("apellido", e.getApellido());
            //contenedor.put("fechaInscripcion", "15/32");
            contenedor.put("celular", e.getCelular());
            contenedor.put("telFijo", e.getTelFijo());
            contenedor.put("email", e.getEmail());
            contenedor.put("grupo", e.getGrupo());

        return (db.insert("estudiante", null, contenedor))>0;
    }

    public boolean eliminar(int id) {
       return db.delete("estudiante","ID="+ id,null)>0;
    }

    public boolean editar(Estudiante e) {
        ContentValues contenedor = new ContentValues();
        contenedor.put("cedula", e.getCedula());
        contenedor.put("pago", e.getPago());
        //contenedor.put("falta", 1);
        contenedor.put("nombre", e.getNombre());
        contenedor.put("apellido", e.getApellido());
        //contenedor.put("fechaInscripcion", "15/32");
        contenedor.put("celular", e.getCelular());
        contenedor.put("telFijo", e.getTelFijo());
        contenedor.put("email", e.getEmail());
        contenedor.put("grupo", e.getGrupo());

        return (db.update("estudiante", contenedor, "ID=" + e.getId(),null))>0;
    }

    public ArrayList<Estudiante> getLstGeneralEstudiantes(int grupo) {
        lstEstudiantes.clear();
        Cursor curso = db.rawQuery("SELECT * FROM ESTUDIANTE WHERE GRUPO='" + grupo + "'", null);
        if (curso != null && curso.getCount() > 0) {
            curso.moveToFirst();
            do {
                //Obtiene elemento por elemento de la DB, lo agrega a un array de tipo Estudiante, para finalmente agregarlo a una lista de estudiantes.
                lstEstudiantes.add(new Estudiante(
                        curso.getInt(0),     //ID
                        curso.getInt(1),     //cedula
                        curso.getString(2),  //nombres
                        curso.getString(3),  //apellidos
                        curso.getString(4),  //celular
                        curso.getString(5),  //telFijo
                        curso.getString(6),   //Email
                        curso.getString(7),    //pago
                        curso.getInt(8)       //grupo
                ));
            } while (curso.moveToNext());
        }

        return lstEstudiantes;
    }
    public ArrayList<Estudiante> getLstEstudiantes() {
        lstEstudiantes.clear();
        Cursor curso = db.rawQuery("SELECT * FROM estudiante", null);
        if (curso != null && curso.getCount() > 0) {
            curso.moveToFirst();
            do {
                //Obtiene elemento por elemento de la DB, lo agrega a un array de tipo Estudiante, para finalmente agregarlo a una lista de estudiantes.
                lstEstudiantes.add(new Estudiante(
                        curso.getInt(0),     //ID
                        curso.getInt(1),     //cedula
                        curso.getString(2),  //nombres
                        curso.getString(3),  //apellidos
                        curso.getString(4),  //celular
                        curso.getString(5),  //telFijo
                        curso.getString(6),   //Email
                        curso.getString(7),    //pago
                        curso.getInt(8)       //grupo
                ));
            } while (curso.moveToNext());
        }

        return lstEstudiantes;
    }

    public Estudiante getEst(int cedula) {
        Cursor curso = db.rawQuery("SELECT * FROM estudiante", null);
        curso.moveToPosition(cedula);
        est = new Estudiante(
                curso.getInt(0),     //ID
                curso.getInt(1),     //cedula
                curso.getString(2),  //nombres
                curso.getString(3),  //apellidos
                curso.getString(4),  //celular
                curso.getString(5),  //telFijo
                curso.getString(6),  //email
                curso.getString(7),   //pago
                curso.getInt(8)       //grupo
        );
        return est;
    }

    public void borrarTabla(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + "ESTUDIANTE");

    }
}
