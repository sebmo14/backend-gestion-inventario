package Servicios;

import Modelo.Producto;
import Repositorios.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Guardar o actualizar un producto
    public Producto guardarProducto(Producto producto) {
        productoRepository.save(producto);
        return producto;
    }

    // Obtener un producto por su id
    public Producto obtenerProducto(Integer id) {
        return productoRepository.findById(id);
    }

    // Obtener todos los productos
    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    // Eliminar un producto
    public void eliminarProducto(Producto producto) {
        productoRepository.delete(producto);
    }
}
