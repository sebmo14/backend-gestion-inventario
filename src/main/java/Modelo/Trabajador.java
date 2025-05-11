package Modelo;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "trabajadores")
public class Trabajador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTrabajadores")
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false)
    private int edad;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false)
    private String contraseña;

    @Column(nullable = false)
    private double salario;

    @Column(nullable = false)
    private int añosExpe;


    public Trabajador(Integer id, String nombre, String apellidos, int edad, String correo, String contraseña, double salario, int añosExpe) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.correo = correo;
        this.contraseña = contraseña;
        this.salario = salario;
        this.añosExpe = añosExpe;
    }

    public Trabajador() {
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getAñosExpe() {
        return añosExpe;
    }

    public void setAñosExpe(int añosExpe) {
        this.añosExpe = añosExpe;
    }

    @Override
    public String toString() {
        return "Trabajador{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", edad=" + edad +
                ", correo='" + correo + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", salario=" + salario +
                ", añosExpe=" + añosExpe +
                '}';
    }
}
