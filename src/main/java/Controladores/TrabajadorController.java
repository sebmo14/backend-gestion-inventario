package Controladores;

import Modelo.Trabajador;
import Servicios.TrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trabajadores")
public class TrabajadorController {

    private final TrabajadorService trabajadorService;

    @Autowired
    public TrabajadorController(TrabajadorService trabajadorService) {
        this.trabajadorService = trabajadorService;
    }

    @GetMapping
    public ResponseEntity<List<Trabajador>> getAllTrabajadores() {
        List<Trabajador> trabajadores = trabajadorService.findAll();
        return new ResponseEntity<>(trabajadores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trabajador> getTrabajador(@PathVariable String id) {
        Trabajador trabajador = trabajadorService.findById(id);
        if (trabajador != null) {
            return new ResponseEntity<>(trabajador, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Trabajador> createTrabajador(@RequestBody Trabajador trabajador) {
        Trabajador newTrabajador = trabajadorService.save(trabajador);
        return new ResponseEntity<>(newTrabajador, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trabajador> updateTrabajador(@PathVariable String id, @RequestBody Trabajador trabajador) {
        Trabajador existingTrabajador = trabajadorService.findById(id);
        if (existingTrabajador != null) {
            trabajador.setId(id);
            Trabajador updatedTrabajador = trabajadorService.update(trabajador);
            return new ResponseEntity<>(updatedTrabajador, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrabajador(@PathVariable String id) {
        Trabajador existingTrabajador = trabajadorService.findById(id);
        if (existingTrabajador != null) {
            trabajadorService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Trabajador>> buscarTrabajador(@RequestParam String nombre) {
        List<Trabajador> trabajadores = trabajadorService.buscarPorFiltros(nombre);
        return new ResponseEntity<>(trabajadores, HttpStatus.OK);
    }

    @GetMapping("/auth")
    public ResponseEntity<Trabajador> getTrabajadorByToken(@RequestHeader("Authorization") String authToken) {
        Trabajador trabajador = trabajadorService.findByAuthToken(authToken);
        if (trabajador != null) {
            return new ResponseEntity<>(trabajador, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
