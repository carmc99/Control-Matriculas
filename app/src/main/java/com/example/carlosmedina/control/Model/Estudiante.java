package com.example.carlosmedina.control.Model;

import java.util.Date;

public class Estudiante {
    private int id;
    private int cedula;
    private String nombre;
    private String apellido;
    private String celular;
    private String telFijo;
    private String email;
    private String genero;
    private String pago;
    private int grupo;
    private String categoria;
    private String fechaDeNacimiento;
    private String nombreAcudiente;
    private String telAcudiente;
    private String fechaDeInscripcion;
    private String fechaUltimoPago;
    private String fechaVencimientoPago;
    private int faltas;


    public Estudiante(int id, int cedula, String nombre, String apellido, String celular, String telFijo, String email, String genero, String pago, int grupo, String categoria, String fechaDeNacimiento, String nombreAcudiente, String telAcudiente, String fechaDeInscripcion, String fechaUltimoPago, String fechaVencimientoPago, int faltas) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.telFijo = telFijo;
        this.email = email;
        this.genero = genero;
        this.pago = pago;
        this.grupo = grupo;
        this.categoria = categoria;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.nombreAcudiente = nombreAcudiente;
        this.telAcudiente = telAcudiente;
        this.fechaDeInscripcion = fechaDeInscripcion;
        this.fechaUltimoPago = fechaUltimoPago;
        this.fechaVencimientoPago = fechaVencimientoPago;
        this.faltas = faltas;
    }

    public Estudiante(int id, int cedula, String nombre, String apellido, String celular, String telFijo, String email, String pago, String categoria, String fechaDeNacimiento, String nombreAcudiente, String telAcudiente, String fechaUltimoPago, String fechaVencimientoPago, int faltas) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.telFijo = telFijo;
        this.email = email;
        this.pago = pago;
        this.categoria = categoria;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.nombreAcudiente = nombreAcudiente;
        this.telAcudiente = telAcudiente;
        this.fechaUltimoPago = fechaUltimoPago;
        this.fechaVencimientoPago = fechaVencimientoPago;
        this.faltas = faltas;
    }

    public Estudiante(int cedula, String nombre, String apellido, String celular, String telFijo, String email, String genero, String pago, int grupo, String categoria, String fechaDeNacimiento, String nombreAcudiente, String telAcudiente, String fechaDeInscripcion, String fechaUltimoPago, String fechaVencimientoPago, int faltas) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.telFijo = telFijo;
        this.email = email;
        this.genero = genero;
        this.pago = pago;
        this.grupo = grupo;
        this.categoria = categoria;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.nombreAcudiente = nombreAcudiente;
        this.telAcudiente = telAcudiente;
        this.fechaDeInscripcion = fechaDeInscripcion;
        this.fechaUltimoPago = fechaUltimoPago;
        this.fechaVencimientoPago = fechaVencimientoPago;
        this.faltas = faltas;
    }

    public String getNombreAcudiente() {
        return nombreAcudiente;
    }

    public void setNombreAcudiente(String nombreAcudiente) {
        this.nombreAcudiente = nombreAcudiente;
    }

    public String getTelAcudiente() {
        return telAcudiente;
    }

    public void setTelAcudiente(String telAcudiente) {
        this.telAcudiente = telAcudiente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
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

    public String getFechaDeInscripcion() {
        return fechaDeInscripcion;
    }

    public void setFechaDeInscripcion(String fechaDeInscripcion) {
        this.fechaDeInscripcion = fechaDeInscripcion;
    }

    public String getFechaUltimoPago() {
        return fechaUltimoPago;
    }

    public void setFechaUltimoPago(String fechaUltimoPago) {
        this.fechaUltimoPago = fechaUltimoPago;
    }

    public String getFechaVencimientoPago() {
        return fechaVencimientoPago;
    }

    public void setFechaVencimientoPago(String fechaVencimientoPago) {
        this.fechaVencimientoPago = fechaVencimientoPago;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}



