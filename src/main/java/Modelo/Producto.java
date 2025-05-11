package Modelo;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProductos")
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "Categorias_ID", nullable = false)
    private Categoria categoria;

    @Column(nullable = false)
    private double precio;


    public Producto(Integer id, String nombre, String descripcion, Categoria categoria, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
    }

    public Producto() {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", categoria=" + categoria +
                ", precio=" + precio +
                '}';
    }
}
