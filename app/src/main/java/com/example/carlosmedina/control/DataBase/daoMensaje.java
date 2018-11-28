package com.example.carlosmedina.control.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.carlosmedina.control.Model.Mensaje;

import java.util.ArrayList;

public class daoMensaje {
    SQLiteDatabase db;
    ArrayList<Mensaje> lstMensajes = new ArrayList<>();
    Mensaje msj;
    Context cx;
    String nombreDb = "DbEstudiantes";

    String tablaMsj = "CREATE TABLE IF NOT EXISTS MENSAJE(ID INTEGER PRIMARY KEY AUTOINCREMENT, AUTOR TEXT, DESCRIPCION TEXT)";

    public daoMensaje(Context cx) {
        this.cx = cx;
        /*String clearDBQuery = "DELETE FROM " + "ESTUDIANTE";
        db.execSQL(clearDBQuery);*/
        db = cx.openOrCreateDatabase(nombreDb, Context.MODE_PRIVATE, null);
        db.execSQL(tablaMsj);
        ingresarMensajes();
    }
    public void ingresarMensajes(){
        ContentValues contenedor = new ContentValues();
        contenedor.put("AUTOR", "Mike Tyson");
        contenedor.put("DESCRIPCION", "Mientras perseveramos y resistimos, podemos conseguir todo lo que queremos");
    }

    public Mensaje getMsj(int id) {
        Cursor curso = db.rawQuery("SELECT * FROM MENSAJE", null); //Corregir
        curso.moveToPosition(id);
        msj = new Mensaje(
                curso.getInt(0),     //ID
                curso.getString(1),     //autor
                curso.getString(2) //mensaje
        );
        return msj;
    }
}
