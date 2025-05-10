package Repositorios;

import Modelo.Reporte;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ReporteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Reporte save(Reporte reporte) {
        if (reporte.getId() == null) {
            entityManager.persist(reporte); // Crear un nuevo reporte
            return reporte;
        } else {
            entityManager.merge(reporte); // Actualizar reporte
            return reporte;
        }
    }

    public Reporte findById(String id) {
        return entityManager.find(Reporte.class, id);
    }

    public List<Reporte> findAll() {
        return entityManager.createQuery("SELECT r FROM Reporte r", Reporte.class).getResultList();
    }

    public void delete(Reporte reporte) {
        entityManager.remove(entityManager.contains(reporte) ? reporte : entityManager.merge(reporte));
    }
}
