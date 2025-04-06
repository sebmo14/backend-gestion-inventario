/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.UUID;


public class Proveedor {
    String id, nombre, email, direccion;
    int numeroTlf;

    public Proveedor() {
        this.id = UUID.randomUUID().toString();
    }

    public Proveedor(String nombre, String email, String direccion, int numeroTlf) {
        this.id = UUID.randomUUID().toString();;
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
        this.numeroTlf = numeroTlf;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String Email) {
        this.email = Email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String Direccion) {
        this.direccion = Direccion;
    }

    public int getNumeroTlf() {
        return numeroTlf;
    }

    public void setNumeroTlf(int numeroTlf) {
        this.numeroTlf = numeroTlf;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "id='" + id + '\'' +
                ", Nombre='" + nombre + '\'' +
                ", Email='" + email + '\'' +
                ", direccion='" + direccion + '\'' +
                ", numeroTlf=" + numeroTlf +
                '}';
    }
}
