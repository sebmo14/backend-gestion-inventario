package Modelo;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "proveedores")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProveedores")
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "Correo", nullable = false, unique = true)
    private String email;

    @Column(name = "Direccion")
    private String direccion;

    @Column(name = "Numero")
    private int numeroTlf;

    public Proveedor() {

    }

    public Proveedor(String nombre, String email, String direccion, int numeroTlf) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
        this.numeroTlf = numeroTlf;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", direccion='" + direccion + '\'' +
                ", numeroTlf=" + numeroTlf +
                '}';
    }
}
