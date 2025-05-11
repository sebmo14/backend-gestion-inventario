package Servicios;

import Modelo.Trabajador;
import Repositorios.TrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrabajadorService {

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    // Guardar o actualizar un trabajador
    public Trabajador guardarTrabajador(Trabajador trabajador) {
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
