package Servicios;

import Modelo.Reporte;
import Repositorios.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    // Guardar o actualizar un reporte
    public Reporte guardarReporte(Reporte reporte) {
        reporteRepository.save(reporte);
        return reporte;
    }

    // Obtener un reporte por su id
    public Reporte obtenerReporte(String id) {
        return reporteRepository.findById(id);
    }

    // Obtener todos los reportes
    public List<Reporte> obtenerTodosLosReportes() {
        return reporteRepository.findAll();
    }

    // Eliminar un reporte
    public void eliminarReporte(Reporte reporte) {
        reporteRepository.delete(reporte);
    }
}
