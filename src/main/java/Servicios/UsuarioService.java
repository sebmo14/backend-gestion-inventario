package Servicios;
import java.util.List;
import Modelo.Usuario;
import Repositorios.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        //initSampleData();
    }

    /*private void initSampleData() {
        Usuario juan = new Usuario("Juan", "Pérez", 30, "juan@example.com", "password123");
        Usuario maria = new Usuario("María", "López", 25, "maria@example.com", "securePass456");
        Usuario carlos = new Usuario("Carlos", "Ruiz", 40, "carlos@example.com", "strongPass789");
        Usuario ana = new Usuario("Ana", "Gómez", 28, "ana@example.com", "safePass321");

        save(juan);
        save(maria);
        save(carlos);
        save(ana);
    }*/

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario findById(String id) {
        return usuarioRepository.findById(id);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario update(Usuario usuario) {
        return usuarioRepository.update(usuario);
    }

    public void deleteById(String id) {
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> buscarPorFiltros(String nombre, String email) {
        return usuarioRepository.buscarPorFiltros(nombre, email);
    }

    public Usuario findByAuthToken(String authToken) {
        return usuarioRepository.findByAuthToken(authToken);
    }
}
