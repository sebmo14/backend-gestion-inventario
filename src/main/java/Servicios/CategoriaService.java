package Servicios;

import Modelo.Categoria;
import Repositorios.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Guardar o actualizar una categoría
    public Categoria guardarCategoria(Categoria categoria) {
        categoriaRepository.save(categoria);
        return categoria;
    }

    // Obtener una categoría por su id
    public Categoria obtenerCategoria(Integer id) {
        return categoriaRepository.findById(id);
    }

    // Obtener todas las categorías
    public List<Categoria> obtenerTodasLasCategorias() {
        return categoriaRepository.findAll();
    }

    // Eliminar una categoría
    public void eliminarCategoria(Categoria categoria) {
        categoriaRepository.delete(categoria);
    }
}
