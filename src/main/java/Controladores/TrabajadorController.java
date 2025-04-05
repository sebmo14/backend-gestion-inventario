package Controladores;

import Modelo.Trabajador;
import Servicios.TrabajadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trabajadores")
@Tag(name = "Trabajadores", description = "Operaciones relacionadas con los trabajadores")
public class TrabajadorController {

    private final TrabajadorService trabajadorService;

    @Autowired
    public TrabajadorController(TrabajadorService trabajadorService) {
        this.trabajadorService = trabajadorService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los trabajadores")
    public ResponseEntity<List<Trabajador>> getAllTrabajadores() {
        return new ResponseEntity<>(trabajadorService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un trabajador por ID")
    public ResponseEntity<Trabajador> getTrabajador(@PathVariable String id) {
        Trabajador trabajador = trabajadorService.findById(id);
        return (trabajador != null) ? new ResponseEntity<>(trabajador, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo trabajador")
    public ResponseEntity<Trabajador> createTrabajador(@RequestBody Trabajador trabajador) {
        return new ResponseEntity<>(trabajadorService.save(trabajador), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un trabajador existente")
    public ResponseEntity<Trabajador> updateTrabajador(@PathVariable String id, @RequestBody Trabajador trabajador) {
        Trabajador existente = trabajadorService.findById(id);
        if (existente != null) {
            trabajador.setId(id);
            return new ResponseEntity<>(trabajadorService.update(trabajador), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un trabajador por ID")
    public ResponseEntity<Void> deleteTrabajador(@PathVariable String id) {
        Trabajador trabajador = trabajadorService.findById(id);
        if (trabajador != null) {
            trabajadorService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar trabajadores por nombre")
    public ResponseEntity<List<Trabajador>> buscarTrabajador(@RequestParam String nombre) {
        return new ResponseEntity<>(trabajadorService.buscarPorFiltros(nombre), HttpStatus.OK);
    }

    @GetMapping("/auth")
    @Operation(summary = "Obtener un trabajador por token de autorización")
    public ResponseEntity<Trabajador> getTrabajadorByToken(@RequestHeader("Authorization") String authToken) {
        Trabajador trabajador = trabajadorService.findByAuthToken(authToken);
        return (trabajador != null) ? new ResponseEntity<>(trabajador, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
