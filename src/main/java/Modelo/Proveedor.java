/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author MI PC
 */
public class Proveedor {
    String Id, Nombre, Email, Direccion;
    int numeroTlf;

    public Proveedor(String Id, String Nombre, String Email, String Direccion, int numeroTlf) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.Email = Email;
        this.Direccion = Direccion;
        this.numeroTlf = numeroTlf;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
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
                "Id='" + Id + '\'' +
                ", Nombre='" + Nombre + '\'' +
                ", Email='" + Email + '\'' +
                ", Direccion='" + Direccion + '\'' +
                ", numeroTlf=" + numeroTlf +
                '}';
    }
}
