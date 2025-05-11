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
        return entityManager.createQuery(
                "SELECT p FROM Producto p",
                Producto.class
        ).getResultList();
    }
    @Transactional
    public void delete(Producto producto) {
        entityManager.remove(entityManager.contains(producto) ? producto : entityManager.merge(producto));
    }

    public List<Producto> buscarPorFiltros(String nombre, String categoria) {
        StringBuilder query = new StringBuilder("SELECT p FROM Producto p WHERE 1=1");

        if (nombre != null && !nombre.isEmpty()) {
            query.append(" AND LOWER(p.nombre) LIKE LOWER(:nombre)");
        }
        if (categoria != null && !categoria.isEmpty()) {
            query.append(" AND LOWER(p.categoria.nombre) = LOWER(:categoria)");
        }

        var q = entityManager.createQuery(query.toString(), Producto.class);

        if (nombre != null && !nombre.isEmpty()) {
            q.setParameter("nombre", "%" + nombre + "%");
        }
        if (categoria != null && !categoria.isEmpty()) {
            q.setParameter("categoria", categoria.toLowerCase());
        }

        return q.getResultList();
    }

}
