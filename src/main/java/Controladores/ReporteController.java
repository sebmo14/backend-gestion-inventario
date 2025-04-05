package Controladores;

import Modelo.Reporte;
import Servicios.ReporteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@Tag(name = "Reportes", description = "Operaciones relacionadas con los reportes")
public class ReporteController {

    private final ReporteService reporteService;

    @Autowired
    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los reportes")
    public ResponseEntity<List<Reporte>> getAllReportes() {
        return new ResponseEntity<>(reporteService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un reporte por ID")
    public ResponseEntity<Reporte> getReporte(@PathVariable String id) {
        Reporte reporte = reporteService.findById(id);
        return (reporte != null) ? new ResponseEntity<>(reporte, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo reporte")
    public ResponseEntity<Reporte> createReporte(@RequestBody Reporte reporte) {
        Reporte newReporte = reporteService.save(reporte);
        return new ResponseEntity<>(newReporte, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un reporte existente")
    public ResponseEntity<Reporte> updateReporte(@PathVariable String id, @RequestBody Reporte reporte) {
        Reporte existingReporte = reporteService.findById(id);
        if (existingReporte != null) {
            reporte.setId(id);
            Reporte updatedReporte = reporteService.update(reporte);
            return new ResponseEntity<>(updatedReporte, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un reporte por ID")
    public ResponseEntity<Void> deleteReporte(@PathVariable String id) {
        Reporte existingReporte = reporteService.findById(id);
        if (existingReporte != null) {
            reporteService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar reportes por nombre (filtro)")
    public ResponseEntity<List<Reporte>> buscarReporte(@RequestParam(required = false) String titulo) {
        return new ResponseEntity<>(reporteService.buscarPorFiltros(titulo), HttpStatus.OK);
    }
}
