package com.example.carlosmedina.control;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tool {


    public boolean validaCorreo(String correo){
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(correo);

        if (mather.find() == true) {
            return true;
        } else {
            return false;
        }

    }
    public boolean esVacio(ArrayList<String> lista){
        for (int i = 0;i<lista.size();i++){
            if (lista.get(i).equals("")){
                return true;
            }
        }
        return false;
    }
    public boolean validaNumero(String numero){
        if (numero.matches("[0-9]+")) {
            return true;
        }
        return false;
    }
    public boolean validaTexto(String texto){
        if (texto.matches("[a-zA-Z_]+")) {
            return true;
        }
        return false;
    }
    public boolean validaCedula(String cedula){
        if (cedula.length()>=5){
            return true;
        }
        return false;
    }
    //Fecha actual
    public String getDate() {
        Date date = new Date();;
        DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        return hourdateFormat.format(date);
    }
}
