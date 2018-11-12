package com.example.carlosmedina.control.Model;

import java.util.Date;

public class Estudiante {
    private int id;
    private String nombre;
    private int cedula;
    private String apellido;
    private int edad;
    private String fechaDeNacimiento;
    private String fechaDeInscripcion;
    private String celular;
    private String telFijo;
    private String email;
    private int faltas;
    private int pago;

    public Estudiante(int id, int cedula,String nombre, String apellido, String celular, String telFijo ) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.telFijo = telFijo;
    }

    public Estudiante(int cedula,String nombre, String apellido, String celular, String telFijo ) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.telFijo = telFijo;
    }


    public Estudiante() {
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

    public int getPago() {
        return pago;
    }

    public void setPago(int pago) {
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





