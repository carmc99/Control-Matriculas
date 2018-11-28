package com.example.carlosmedina.control.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.carlosmedina.control.Model.Estudiante;
import com.example.carlosmedina.control.Model.Grupo;

import java.util.ArrayList;

public class daoGrupo {
    SQLiteDatabase db;
    ArrayList<Grupo> lstGrupos = new ArrayList<>();
    Grupo group;
    Context cx;
    String nombreDb = "DbEstudiantes";
    String tablaGrupos = "CREATE TABLE IF NOT EXISTS GRUPO(ID INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT, DESCRIPCION TEXT)";
    public daoGrupo(Context cx) {
        this.cx = cx;
        /*String clearDBQuery = "DELETE FROM " + "ESTUDIANTE";
        db.execSQL(clearDBQuery);*/
        db = cx.openOrCreateDatabase(nombreDb, Context.MODE_PRIVATE, null);
        db.execSQL(tablaGrupos);


    }
    public boolean insertar(Grupo g){
        ContentValues contenedor = new ContentValues();
        contenedor.put("NOMBRE", g.getNombre());
        contenedor.put("DESCRIPCION", g.getDescripcion());

        return (db.insert("GRUPO", null, contenedor))>0;
    }
    public boolean editar(Grupo g){
        return true;
    }
    public boolean eliminar(Grupo g){
        return true;
    }
    public ArrayList<Grupo> getLstGrupos(){
        lstGrupos.clear();
        Cursor curso = db.rawQuery("SELECT * FROM GRUPO", null);
        if (curso != null && curso.getCount() > 0) {
            curso.moveToFirst();
            do {
                //Obtiene elemento por elemento de la DB, lo agrega a un array de tipo grupo, para finalmente agregarlo a una lista de grupos.
                lstGrupos.add(new Grupo(
                        curso.getInt(0),     //ID
                        curso.getString(1),  //Nombre
                        curso.getString(2)  //Descripcion
                ));
            } while (curso.moveToNext());
        }

        return lstGrupos;
    }
    /*
    public Grupo getGrupo(int id){

    }*/
}
