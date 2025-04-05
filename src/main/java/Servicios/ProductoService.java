package Servicios;


import Modelo.Categoria;
import Modelo.Producto;
import Repositorios.CategoriaRepository;
import Repositorios.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Servicios.CategoriaService;

import java.time.LocalDate;
import java.util.List;

@Service

public class ProductoService {
    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
        initSampleData();
    }

    private void initSampleData() {
        Categoria tecnologia = new Categoria("Tecnología", "Productos electrónicos y gadgets", LocalDate.of(2024, 1, 10));
        Categoria electrodomesticos = new Categoria("Electrodomésticos", "Equipos para el hogar", LocalDate.of(2024, 2, 5));

        Producto laptop = new Producto("Laptop Gamer", "Laptop con procesador i7 y 16GB RAM", tecnologia, 1200.99);
        Producto cafetera = new Producto("Cafetera", "Cafetera automática con espumador de leche", electrodomesticos, 149.99);

        save(laptop);
        save(cafetera);
    }


    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto findById(String id) {
        return productoRepository.findById(id);
    }

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Producto update(Producto producto) {
        return productoRepository.update(producto);
    }

    public void deleteById(String id) {
        productoRepository.deleteById(id);
    }

    public List<Producto> buscarPorFiltros(String nombre, String categoria) {
        return productoRepository.buscarPorFiltros(nombre, categoria);
    }

    public Producto findByAuthToken(String authToken) {
        return productoRepository.findByAuthToken(authToken);
    }
}
