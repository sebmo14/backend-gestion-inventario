package Servicios;

import Modelo.Producto;
import Repositorios.ProductoRepository;
import jakarta.transaction.Transactional;
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
    @Transactional
    public void eliminarProducto(Producto producto) {
        productoRepository.delete(producto);
    }

    public List<Producto> obtenerProductosPorFiltro(String nombre, String categoria) {
        return productoRepository.buscarPorFiltros(nombre, categoria);
    }
}
