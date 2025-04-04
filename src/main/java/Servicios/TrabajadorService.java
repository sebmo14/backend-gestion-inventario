package Servicios;

import Modelo.Trabajador;
import Modelo.Usuario;
import Repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TrabajadorService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public TrabajadorService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
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
        return (Trabajador) usuarioRepository.save(trabajador);
    }

    public Usuario findById(String id) {
        return usuarioRepository.findTrabajadorById(id);
    }

    public List<Trabajador> findAll() {
        return usuarioRepository.findAllTrabajadores();
    }

    public Trabajador update(Trabajador trabajador) {
        return usuarioRepository.updateTrabajador(trabajador);
    }

    public void deleteById(String id) {
        usuarioRepository.deleteTrabajadorById(id);
    }

    public List<Usuario> buscarPorFiltros(String nombre, String email) {
        return usuarioRepository.buscarPorFiltros(nombre, email);
    }

    public Usuario findByAuthToken(String authToken) {
        return usuarioRepository.findByAuthToken(authToken);
    }
}
