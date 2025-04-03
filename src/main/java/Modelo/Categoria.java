package Modelo;


import java.time.LocalDate;
import java.util.UUID;

public class Categoria {

    String nombre, descripcion;
    String id;
    LocalDate fechaCreacion;

    public Categoria() {
        this.id = UUID.randomUUID().toString();;
    }

    public Categoria(String nombre) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", id=" + id +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
}
