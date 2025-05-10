package Repositorios;

import Modelo.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ProductoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Producto save(Producto producto) {
        if (producto.getId() == null) {
            entityManager.persist(producto); // Crear un nuevo producto
            return producto;
        } else {
            entityManager.merge(producto); // Actualizar el producto
            return producto;
        }
    }

    public Producto findById(Integer id) {
        return entityManager.find(Producto.class, id);
    }

    public List<Producto> findAll() {
        return entityManager.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
    }

    public void delete(Producto producto) {
        entityManager.remove(entityManager.contains(producto) ? producto : entityManager.merge(producto));
    }
}
