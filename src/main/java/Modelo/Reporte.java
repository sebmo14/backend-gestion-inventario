package Modelo;

import java.time.LocalDate;
import java.util.UUID;

public class Reporte {
    private String id;
    private String nombre;
    private LocalDate fecha;

    public Reporte() {
        this.id = UUID.randomUUID().toString();;
    }
    public Reporte(String nombre, LocalDate fecha) {
        this.id = UUID.randomUUID().toString();;
        this.nombre = nombre;
        this.fecha = fecha;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Reporte{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}
