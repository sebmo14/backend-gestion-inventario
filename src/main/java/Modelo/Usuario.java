package Modelo;
import java.util.UUID;

public abstract class Usuario {
    //Atributos
    protected String nombre;
    protected String apellidos;
    protected int edad;
    protected String correo;
    protected String id;
    protected String contraseña;
    
    //Constructores


    public Usuario() {
        this.id = UUID.randomUUID().toString();;
    }

    public Usuario(String nombre, String apellidos, int edad, String correo, String contraseña) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.correo = correo;
        this.id = UUID.randomUUID().toString();;
        this.contraseña = contraseña;
    }
    
     //Get and set

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

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", edad=" + edad +
                ", correo='" + correo + '\'' +
                ", id=" + id +
                ", contraseña='" + contraseña + '\'' +
                '}';
    }
}
