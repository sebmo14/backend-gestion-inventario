package Servicios;

import Modelo.Trabajador;
import Repositorios.TrabajadorRepository;
import com.example.demo.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Import(SecurityConfig.class) // Add this to import the SecurityConfig class
public class TrabajadorService {

    private final TrabajadorRepository trabajadorRepository;
    private final PasswordEncoder passwordEncoder; // Changed to final for best practice

    @Autowired
    public TrabajadorService(TrabajadorRepository trabajadorRepository, PasswordEncoder passwordEncoder) {
        this.trabajadorRepository = trabajadorRepository;
        this.passwordEncoder = passwordEncoder; // Proper constructor injection
    }

    // Guardar o actualizar un trabajador
    public Trabajador guardarTrabajador(Trabajador trabajador) {
        trabajador.setContraseña(passwordEncoder.encode(trabajador.getContraseña()));
        trabajadorRepository.save(trabajador);
        return trabajador;
    }

    // Obtener un trabajador por su id
    public Trabajador obtenerTrabajador(Integer id) {
        return trabajadorRepository.findById(id);
    }

    // Obtener todos los trabajadores
    public List<Trabajador> obtenerTodosLosTrabajadores() {
        return trabajadorRepository.findAll();
    }

    // Eliminar un trabajador
    public void eliminarTrabajador(Trabajador trabajador) {
        trabajadorRepository.delete(trabajador);
    }

    public List<Trabajador> obtenerTrabajadoresPorFiltro(String nombre) {
        return trabajadorRepository.buscarPorFiltro(nombre);
    }

    public Trabajador obtenerTrabajadorPorEmail(String email) {
        return trabajadorRepository.findByEmail(email);
    }
}