package Servicios;

import Modelo.Reporte;
import Repositorios.ReporteRepository;
import Repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service

public class ReporteService {
    private final ReporteRepository reporteRepository;

    @Autowired
    public ReporteService(ReporteRepository reporteRepository) {
        this.reporteRepository = reporteRepository;
        //initSampleData();
    }

    private void initSampleData() {
        Reporte reporteVentas = new Reporte("Reporte de Ventas", LocalDate.of(2024, 3, 15));
        Reporte reporteGastos = new Reporte("Reporte de Gastos", LocalDate.of(2024, 3, 20));
        Reporte reporteInventario = new Reporte("Reporte de Inventario", LocalDate.of(2024, 3, 25));
        Reporte reporteEmpleados = new Reporte("Reporte de Empleados", LocalDate.of(2024, 3, 30));

        save(reporteVentas);
        save(reporteGastos);
        save(reporteInventario);
        save(reporteEmpleados);
    }


    public Reporte save(Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    public Reporte findById(String id) {
        return reporteRepository.findById(id);
    }

    public List<Reporte> findAll() {
        return reporteRepository.findAll();
    }

    public Reporte update(Reporte reporte) {
        return reporteRepository.update(reporte);
    }

    public void deleteById(String id) {
        reporteRepository.deleteById(id);
    }

    public List<Reporte> buscarPorFiltros(String nombre) {
        return reporteRepository.buscarPorFiltros(nombre);
    }

    public Reporte findByAuthToken(String authToken) {
        return reporteRepository.findByAuthToken(authToken);
    }
}
