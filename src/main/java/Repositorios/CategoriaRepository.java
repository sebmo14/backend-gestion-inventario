package Repositorios;

import Modelo.Categoria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public class CategoriaRepository {


    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Categoria save(Categoria categoria) {
        if (categoria.getId() == null) {
            entityManager.persist(categoria); // Crear una nueva entidad
            return categoria;
        } else {
            entityManager.merge(categoria); // Actualizar una entidad existente
            return categoria;
        }
    }

    public Categoria findById(Integer id) {
        return entityManager.find(Categoria.class, id);
    }

    public List<Categoria> findAll() {
        return entityManager.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
    }
    @Transactional
    public void delete(Categoria categoria) {
        entityManager.remove(entityManager.contains(categoria) ? categoria : entityManager.merge(categoria));
    }
    public Categoria findByNombre(String nombre) {
        try {
            return entityManager.createQuery("SELECT c FROM Categoria c WHERE c.nombre = :nombre", Categoria.class)
                    .setParameter("nombre", nombre)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
