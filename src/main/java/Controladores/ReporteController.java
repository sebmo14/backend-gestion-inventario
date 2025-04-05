package Controladores;

import Modelo.Reporte;
import Servicios.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    @Autowired
    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping
    public ResponseEntity<List<Reporte>> getAllReportes() {
        List<Reporte> reportes = reporteService.findAll();
        return new ResponseEntity<>(reportes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reporte> getReporte(@PathVariable String id) {
        Reporte reporte = reporteService.findById(id);
        if (reporte != null) {
            return new ResponseEntity<>(reporte, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Reporte> createReporte(@RequestBody Reporte reporte) {
        Reporte newReporte = reporteService.save(reporte);
        return new ResponseEntity<>(newReporte, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reporte> updateReporte(@PathVariable String id, @RequestBody Reporte reporte) {
        Reporte existingReporte = reporteService.findById(id);
        if (existingReporte != null) {
            reporte.setId(id);
            Reporte updatedReporte = reporteService.update(reporte);
            return new ResponseEntity<>(updatedReporte, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReporte(@PathVariable String id) {
        Reporte existingReporte = reporteService.findById(id);
        if (existingReporte != null) {
            reporteService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Opcional: búsqueda por filtros si lo necesitas
    @GetMapping("/buscar")
    public ResponseEntity<List<Reporte>> buscarReporte(@RequestParam(required = false) String titulo) {
        List<Reporte> reportes = reporteService.buscarPorFiltros(titulo);
        return new ResponseEntity<>(reportes, HttpStatus.OK);
    }
}
