
package Modelo;

import java.util.UUID;

/**
 *
 * @author MI PC
 */
public class Trabajador {
    //Atributos
    private String nombre;
    private String apellidos;
    private int edad;
    private String correo;
    private String id;
    private String contraseña;
    private double salario;
    private int añosExpe;

    //Constructor

    public Trabajador(String nombre, String apellidos, int edad, String correo, String contraseña, double salario, int añosExpe) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.correo = correo;
        this.id = UUID.randomUUID().toString();
        this.contraseña = contraseña;
        this.salario = salario;
        this.añosExpe = añosExpe;
    }

    public Trabajador() {
        this.id = UUID.randomUUID().toString();
    }


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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", edad=" + edad +
                ", correo='" + correo + '\'' +
                ", id='" + id + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", salario=" + salario +
                ", añosExpe=" + añosExpe +
                '}';
    }
}
