package Controladores;

import Modelo.Reporte;
import Servicios.ReporteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Obtener todos los reportes", description = "Devuelve una lista con todos los reportes registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de reportes obtenida correctamente")
    })
    public ResponseEntity<List<Reporte>> getAllReportes() {
        return new ResponseEntity<>(reporteService.obtenerTodosLosReportes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un reporte por ID", description = "Busca y devuelve un reporte usando su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reporte encontrado"),
            @ApiResponse(responseCode = "404", description = "Reporte no encontrado")
    })
    public ResponseEntity<Reporte> getReporte(
            @Parameter(description = "ID del reporte que se desea buscar", required = true)
            @PathVariable String id) {
        Reporte reporte = reporteService.obtenerReporte(id);
        return (reporte != null) ? new ResponseEntity<>(reporte, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo reporte", description = "Registra un nuevo reporte en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reporte creado exitosamente")
    })
    public ResponseEntity<Reporte> createReporte(
            @Parameter(description = "Objeto Reporte a crear", required = true)
            @RequestBody Reporte reporte) {
        Reporte newReporte = reporteService.guardarReporte(reporte);
        return new ResponseEntity<>(newReporte, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un reporte existente", description = "Actualiza los datos de un reporte existente por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reporte actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Reporte no encontrado")
    })
    public ResponseEntity<Reporte> updateReporte(
            @Parameter(description = "ID del reporte que se desea actualizar", required = true)
            @PathVariable String id,
            @Parameter(description = "Objeto Reporte actualizado", required = true)
            @RequestBody Reporte reporte) {
        Reporte existingReporte = reporteService.obtenerReporte(id);
        if (existingReporte != null) {
            reporte.setId(id);
            Reporte updatedReporte = reporteService.guardarReporte(reporte);
            return new ResponseEntity<>(updatedReporte, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un reporte por ID", description = "Elimina un reporte del sistema usando su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Reporte eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Reporte no encontrado")
    })
    public ResponseEntity<Void> deleteReporte(
            @Parameter(description = "ID del reporte que se desea eliminar", required = true)
            @PathVariable String id) {
        Reporte existingReporte = reporteService.obtenerReporte(id);
        if (existingReporte != null) {
            reporteService.eliminarReporte(existingReporte);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar reportes por título", description = "Busca reportes que contengan un texto en el título.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reportes encontrados")
    })
    public ResponseEntity<List<Reporte>> buscarReporte(
            @Parameter(description = "Título a buscar (puede ser parcial)", required = false)
            @RequestParam(required = false) String titulo) {
        return new ResponseEntity<>(reporteService.buscarPorFiltros(titulo), HttpStatus.OK);
    }
}
