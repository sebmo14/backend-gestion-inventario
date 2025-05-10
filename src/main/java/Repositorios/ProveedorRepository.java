package Repositorios;

import Modelo.Proveedor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ProveedorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Proveedor save(Proveedor proveedor) {
        if (proveedor.getId() == null) {
            entityManager.persist(proveedor); // Crear un nuevo proveedor
            return proveedor;
        } else {
            entityManager.merge(proveedor); // Actualizar proveedor
            return proveedor;
        }
    }

    public Proveedor findById(String id) {
        return entityManager.find(Proveedor.class, id);
    }

    public List<Proveedor> findAll() {
        return entityManager.createQuery("SELECT p FROM Proveedor p", Proveedor.class).getResultList();
    }

    public void delete(Proveedor proveedor) {
        entityManager.remove(entityManager.contains(proveedor) ? proveedor : entityManager.merge(proveedor));
    }
}
