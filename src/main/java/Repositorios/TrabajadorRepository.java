package Repositorios;

import Modelo.Proveedor;
import Modelo.Trabajador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class TrabajadorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Trabajador save(Trabajador trabajador) {
        if (trabajador.getId() == null) {
            entityManager.persist(trabajador); // Crear un nuevo trabajador
            return trabajador;
        } else {
            entityManager.merge(trabajador); // Actualizar trabajador
            return trabajador;
        }
    }

    public TrabajadorRepository() {
    }

    public Trabajador findById(Integer id) {
        return entityManager.find(Trabajador.class, id);
    }

    public List<Trabajador> findAll() {
        return entityManager.createQuery("SELECT t FROM Trabajador t", Trabajador.class).getResultList();
    }

    public void delete(Trabajador trabajador) {
        entityManager.remove(entityManager.contains(trabajador) ? trabajador : entityManager.merge(trabajador));
    }

    public List<Trabajador> buscarPorFiltro(String nombre) {
        return entityManager.createQuery(
                        "SELECT p FROM Trabajador p WHERE LOWER(p.nombre) LIKE LOWER(:nombre)",
                        Trabajador.class)
                .setParameter("nombre", "%" + nombre + "%")
                .getResultList();
    }
    public Trabajador findByEmail(String correo) {
        List<Trabajador> resultados = entityManager.createQuery(
                        "SELECT t FROM Trabajador t WHERE t.correo = :correo", Trabajador.class)
                .setParameter("correo", correo)
                .getResultList();

        return resultados.isEmpty() ? null : resultados.get(0);
    }


}
