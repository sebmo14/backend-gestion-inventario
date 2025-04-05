package Servicios;

import Modelo.Trabajador;
import Repositorios.TrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TrabajadorService {
    private final TrabajadorRepository trabajadorRepository;

    @Autowired
    public TrabajadorService(TrabajadorRepository trabajadorRepository) {
        this.trabajadorRepository = trabajadorRepository;
        initSampleData();
    }

    private void initSampleData() {
        Trabajador juan = new Trabajador("Juan", "Pérez", 30, "juan@example.com", "password123", 2500.0, 5);
        Trabajador maria = new Trabajador("María", "López", 25, "maria@example.com", "securePass456", 2200.0, 3);
        Trabajador carlos = new Trabajador("Carlos", "Ruiz", 40, "carlos@example.com", "strongPass789", 3000.0, 15);
        Trabajador ana = new Trabajador("Ana", "Gómez", 28, "ana@example.com", "safePass321", 2700.0, 7);

        save(juan);
        save(maria);
        save(carlos);
        save(ana);
    }

    public Trabajador save(Trabajador trabajador) {
        return (Trabajador) trabajadorRepository.save(trabajador);
    }

    public Trabajador findById(String id) {
        return trabajadorRepository.findById(id);
    }

    public List<Trabajador> findAll() {
        return trabajadorRepository.findAll();
    }

    public Trabajador update(Trabajador trabajador) {
        return trabajadorRepository.update(trabajador);
    }

    public void deleteById(String id) {
        trabajadorRepository.deleteById(id);
    }

    public List<Trabajador> buscarPorFiltros(String nombre) {
        return trabajadorRepository.buscarPorFiltros(nombre);
    }

    public Trabajador findByAuthToken(String authToken) {
        return trabajadorRepository.findByAuthToken(authToken);
    }
}
