package com.example.recycleit;

public class Recolector {

    private String Nombre;
    private String Telefono;

    public Recolector(){

    }

    public Recolector(String nombre, String telefono) {
        Nombre = nombre;
        Telefono = telefono;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }
}
