package Servicios;

import Modelo.Categoria;
import Repositorios.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service

public class CategoriaService {

    private final CategoriaRepository categoriaRepository;


    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
        initSampleData();
    }




    private void initSampleData() {
        Categoria tecnologia = new Categoria("Tecnología", "Productos electrónicos y gadgets", LocalDate.of(2024, 1, 10));
        Categoria electrodomesticos = new Categoria("Electrodomésticos", "Equipos para el hogar", LocalDate.of(2024, 2, 5));
        Categoria muebles = new Categoria("Muebles", "Sillas, mesas y estanterías", LocalDate.of(2024, 3, 15));
        Categoria ropa = new Categoria("Ropa", "Prendas de vestir y accesorios", LocalDate.of(2024, 4, 1));

        save(tecnologia);
        save(electrodomesticos);
        save(muebles);
        save(ropa);
    }


    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria findById(String id) {
        return categoriaRepository.findById(id);
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria update(Categoria categoria) {
        return categoriaRepository.update(categoria);
    }

    public void deleteById(String id) {
        categoriaRepository.deleteById(id);
    }

    public List<Categoria> buscarPorFiltros(String nombre) {
        return categoriaRepository.buscarPorFiltros(nombre);
    }

    public Categoria findByAuthToken(String authToken) {
        return categoriaRepository.findByAuthToken(authToken);
    }
}
