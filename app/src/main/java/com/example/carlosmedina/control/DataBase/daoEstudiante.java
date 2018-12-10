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
    //String tabla = "CREATE TABLE IF NOT EXISTS ESTUDIANTE(ID INTEGER PRIMARY KEY AUTOINCREMENT, CEDULA INTEGER, NOMBRE TEXT," +
    //        "APELLIDO TEXT, CELULAR TEXT, TELFIJO TEXT, EMAIL TEXT, PAGO TEXT, GRUPO INTEGER)";
    String tabla = "CREATE TABLE IF NOT EXISTS ESTUDIANTE(ID INTEGER PRIMARY KEY AUTOINCREMENT, CEDULA INTEGER, NOMBRE TEXT," +
           "APELLIDO TEXT, CELULAR TEXT, TELFIJO TEXT, EMAIL TEXT, GENERO TEXT,PAGO TEXT, GRUPO INTEGER, CATEGORIA TEXT, FECHA_NACIMIENTO TEXT," +
            "ACUDIENTE TEXT, TEL_ACUDIENTE TEXT, FECHA_INCRIPCION TEXT, FECHA_ULTIMO_PAGO TEXT, FECHA_VENCIMIENTO_PAGO TEXT,"+
            "NUM_FALTAS INTEGER)";
    public daoEstudiante(Context cx) {
        this.cx = cx;
        /*String clearDBQuery = "DELETE FROM " + "ESTUDIANTE";
        db.execSQL(clearDBQuery); */
        db = cx.openOrCreateDatabase(nombreDb, Context.MODE_PRIVATE, null);
        db.execSQL(tabla);
        //db.execSQL(detalleEst);
    }

    public boolean insertar(Estudiante e) {
            int cedula;
            boolean repetido = false;
            Cursor curso = db.rawQuery("SELECT CEDULA FROM ESTUDIANTE WHERE CEDULA='" + e.getCedula() + "'", null);
            if (curso != null && curso.getCount() > 0) {
                curso.moveToFirst();
                do {
                    cedula = curso.getInt(1);          //CEDULA
                    if(cedula==e.getCedula()){
                        repetido = true;
                    }
                } while (curso.moveToNext());
            }
            ContentValues contenedor = new ContentValues();
            //Información personal:
            contenedor.put("CEDULA", e.getCedula());
            contenedor.put("NOMBRE", e.getNombre());
            contenedor.put("APELLIDO", e.getApellido());
            contenedor.put("GENERO", e.getGrupo());
            contenedor.put("CELULAR", e.getCelular());
            contenedor.put("TELFIJO", e.getTelFijo());
            contenedor.put("EMAIL", e.getEmail());
            contenedor.put("GRUPO", e.getGrupo());
            contenedor.put("CATEGORIA", e.getCategoria());
            contenedor.put("FECHA_INCRIPCION", e.getFechaDeInscripcion());
            contenedor.put("FECHA_NACIMIENTO", e.getFechaDeNacimiento());
            contenedor.put("NUM_FALTAS", e.getFaltas());
            //Información acudiente
            contenedor.put("ACUDIENTE", e.getNombreAcudiente());
            contenedor.put("TEL_ACUDIENTE", e.getTelAcudiente());
            //Información contable:
            contenedor.put("PAGO", e.getPago());
            contenedor.put("FECHA_ULTIMO_PAGO", e.getFechaUltimoPago());
            contenedor.put("FECHA_VENCIMIENTO_PAGO", e.getFechaVencimientoPago());
        if (!repetido){
            return (db.insert("ESTUDIANTE", null, contenedor))>0;
        }
        return false;
    }



    public boolean eliminar(int id) {
       return db.delete("ESTUDIANTE","ID="+ id,null)>0;
    }

    public boolean editar(Estudiante e) {
        ContentValues contenedor = new ContentValues();
        //Información personal:
        contenedor.put("CEDULA", e.getCedula());
        contenedor.put("NOMBRE", e.getNombre());
        contenedor.put("APELLIDO", e.getApellido());
        contenedor.put("CELULAR", e.getCelular());
        contenedor.put("TELFIJO", e.getTelFijo());
        contenedor.put("EMAIL", e.getEmail());
        contenedor.put("CATEGORIA", e.getCategoria());
        contenedor.put("FECHA_NACIMIENTO", e.getFechaDeNacimiento());
        contenedor.put("NUM_FALTAS", e.getFaltas());
        //Información acudiente
        contenedor.put("ACUDIENTE", e.getNombreAcudiente());
        contenedor.put("TEL_ACUDIENTE", e.getTelAcudiente());
        //Información contable:
        contenedor.put("PAGO", e.getPago());
        contenedor.put("FECHA_ULTIMO_PAGO", e.getFechaUltimoPago());
        contenedor.put("FECHA_VENCIMIENTO_PAGO", e.getFechaVencimientoPago());

        return (db.update("ESTUDIANTE", contenedor, "ID=" + e.getId(),null))>0;
    }
    public ArrayList<Estudiante> getLstGeneralEstudiantes(int grupo) {
        lstEstudiantes.clear();
        Cursor curso = db.rawQuery("SELECT * FROM ESTUDIANTE WHERE GRUPO='" + grupo + "'", null);
        if (curso != null && curso.getCount() > 0) {
            curso.moveToFirst();
            do {
                //Obtiene elemento por elemento de la DB, lo agrega a un array de tipo Estudiante, para finalmente agregarlo a una lista de estudiantes.
                lstEstudiantes.add(new Estudiante(
                        curso.getInt(0),           //ID
                        curso.getInt(1),           //CEDULA
                        curso.getString(2),        //NOMBRE
                        curso.getString(3),        //APELLIDO
                        curso.getString(4),        //CELULAR
                        curso.getString(5),        //TELFIJO
                        curso.getString(6),        //EMAIL
                        curso.getString(7),        //GENERO
                        curso.getString(8),        //PAGO
                        curso.getInt(9),           //GRUPO
                        curso.getString(10),       //CATEGORIA
                        curso.getString(11),       //FECHA_NACIMIENTO
                        curso.getString(12),       //ACUDIENTE
                        curso.getString(13),       //TEL_ACUDIENTE
                        curso.getString(14),       //FECHA_INCRIPCION
                        curso.getString(15),       //FECHA_ULTIMO_PAGO
                        curso.getString(16),       //FECHA_VENCIMIENTO_PAGO
                        curso.getInt(17)       //NUM_FALTAS
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
                        curso.getInt(0),           //ID
                        curso.getInt(1),           //CEDULA
                        curso.getString(2),        //NOMBRE
                        curso.getString(3),        //APELLIDO
                        curso.getString(4),        //CELULAR
                        curso.getString(5),        //TELFIJO
                        curso.getString(6),        //EMAIL
                        curso.getString(7),        //GENERO
                        curso.getString(8),        //PAGO
                        curso.getInt(9),           //GRUPO
                        curso.getString(10),       //CATEGORIA
                        curso.getString(11),       //FECHA_NACIMIENTO
                        curso.getString(12),       //ACUDIENTE
                        curso.getString(13),       //TEL_ACUDIENTE
                        curso.getString(14),       //FECHA_INCRIPCION
                        curso.getString(15),       //FECHA_ULTIMO_PAGO
                        curso.getString(16),       //FECHA_VENCIMIENTO_PAGO
                        curso.getInt(17)       //NUM_FALTAS
                ));
            } while (curso.moveToNext());
        }

        return lstEstudiantes;
    }

    public Estudiante getEst(int cedula) {
        Cursor curso = db.rawQuery("SELECT * FROM estudiante", null);
        curso.moveToPosition(cedula);
        est = new Estudiante(
                curso.getInt(0),           //ID
                curso.getInt(1),           //CEDULA
                curso.getString(2),        //NOMBRE
                curso.getString(3),        //APELLIDO
                curso.getString(4),        //CELULAR
                curso.getString(5),        //TELFIJO
                curso.getString(6),        //EMAIL
                curso.getString(7),        //GENERO
                curso.getString(8),        //PAGO
                curso.getInt(9),           //GRUPO
                curso.getString(10),       //CATEGORIA
                curso.getString(11),       //FECHA_NACIMIENTO
                curso.getString(12),       //ACUDIENTE
                curso.getString(13),       //TEL_ACUDIENTE
                curso.getString(14),       //FECHA_INCRIPCION
                curso.getString(15),       //FECHA_ULTIMO_PAGO
                curso.getString(16),       //FECHA_VENCIMIENTO_PAGO
                curso.getInt(17)       //NUM_FALTAS
        );
        return est;
    }

    public void borrarTabla(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + "ESTUDIANTE");

    }
}
