package com.example.carlosmedina.control.Model;

public class Mensaje {
    private int id;
    private String autor;
    private String mensaje;
    private final String msj = "Mientras perseveramos y resistimos, podemos conseguir todo lo que queremos";

    public Mensaje(int id, String autor, String mensaje) {
        this.id = id;
        this.autor = autor;
        this.mensaje = mensaje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMsj() {
        return msj;
    }
}
