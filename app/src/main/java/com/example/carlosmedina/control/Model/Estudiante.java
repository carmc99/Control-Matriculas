package com.example.carlosmedina.control.Model;

import java.util.Date;

public class Estudiante {
    private int id;
    private String nombre;
    private int cedula;
    private String apellido;
    private String genero;
    private int grupo;
    private int edad;
    private String fechaDeNacimiento;
    private String fechaDeInscripcion;
    private String celular;
    private String telFijo;
    private String email;
    private int faltas;
    private String pago;

    public Estudiante(int id, int cedula,String nombre, String apellido, String celular, String telFijo, String email, String pago) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.telFijo = telFijo;
        this.email = email;
        this.pago = pago;
        this.grupo = grupo;
    }
    public Estudiante(int id, int cedula,String nombre, String apellido, String celular, String telFijo, String email, String pago,int grupo) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.telFijo = telFijo;
        this.email = email;
        this.pago = pago;
        this.grupo = grupo;
    }


    public Estudiante(int cedula,String nombre, String apellido, String celular, String telFijo, String email, String pago, int grupo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.telFijo = telFijo;
        this.email = email;
        this.pago = pago;
        this.grupo = grupo;
    }




    public Estudiante() {
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public int getGrupo() {
        return grupo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getFechaDeInscripcion() {
        return fechaDeInscripcion;
    }

    public void setFechaDeInscripcion(String fechaDeInscripcion) {
        this.fechaDeInscripcion = fechaDeInscripcion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelFijo() {
        return telFijo;
    }

    public void setTelFijo(String telFijo) {
        this.telFijo = telFijo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}





