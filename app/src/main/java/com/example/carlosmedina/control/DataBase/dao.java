package com.example.carlosmedina.control.DataBase;

import java.util.ArrayList;

public interface dao {
    boolean insertar(Object e);
    boolean eliminar(int id);
    boolean editar(Object e);
    ArrayList<Object> getLst();
    Object getItem(int id);
}
