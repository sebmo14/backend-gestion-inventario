package Controladores;

import DTO.LoginRequest;
import Modelo.Trabajador;
import Servicios.TrabajadorService;
import com.example.demo.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trabajadores")
@Tag(name = "Trabajadores", description = "Operaciones relacionadas con los trabajadores")
@Import(SecurityConfig.class)
public class TrabajadorController {

    private final TrabajadorService trabajadorService;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public TrabajadorController(TrabajadorService trabajadorService, PasswordEncoder passwordEncoder) {
        this.trabajadorService = trabajadorService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los trabajadores", description = "Devuelve una lista con todos los trabajadores registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida con éxito")
    })
    public ResponseEntity<List<Trabajador>> getAllTrabajadores() {
        return new ResponseEntity<>(trabajadorService.obtenerTodosLosTrabajadores(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un trabajador por ID", description = "Busca un trabajador específico usando su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trabajador encontrado"),
            @ApiResponse(responseCode = "404", description = "Trabajador no encontrado")
    })
    public ResponseEntity<Trabajador> getTrabajador(@PathVariable Integer id) {
        Trabajador trabajador = trabajadorService.obtenerTrabajador(id);
        return (trabajador != null) ? new ResponseEntity<>(trabajador, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo trabajador", description = "Registra un nuevo trabajador en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Trabajador creado correctamente")
    })
    public ResponseEntity<Trabajador> createTrabajador(@RequestBody Trabajador trabajador) {
        return new ResponseEntity<>(trabajadorService.guardarTrabajador(trabajador), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un trabajador existente", description = "Actualiza los datos de un trabajador existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trabajador actualizado"),
            @ApiResponse(responseCode = "404", description = "Trabajador no encontrado")
    })
    public ResponseEntity<Trabajador> updateTrabajador(@PathVariable Integer id, @RequestBody Trabajador trabajador) {
        Trabajador existente = trabajadorService.obtenerTrabajador(id);
        if (existente != null) {
            trabajador.setId(id);
            return new ResponseEntity<>(trabajadorService.guardarTrabajador(trabajador), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un trabajador por ID", description = "Elimina un trabajador del sistema según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Trabajador eliminado"),
            @ApiResponse(responseCode = "404", description = "Trabajador no encontrado")
    })
    public ResponseEntity<Void> deleteTrabajador(@PathVariable Integer id) {
        Trabajador trabajador = trabajadorService.obtenerTrabajador(id);
        if (trabajador != null) {
            trabajadorService.eliminarTrabajador(trabajador);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar trabajadores por nombre", description = "Permite buscar trabajadores que contengan un nombre parcial o completo.")
    @ApiResponse(responseCode = "200", description = "Trabajadores encontrados")
    public ResponseEntity<List<Trabajador>> buscarTrabajador(@RequestParam String nombre) {
        return new ResponseEntity<>(trabajadorService.obtenerTrabajadoresPorFiltro(nombre), HttpStatus.OK);
    }

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión como trabajador", description = "Permite a un trabajador autenticarse con email y contraseña.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login exitoso"),
            @ApiResponse(responseCode = "401", description = "Credenciales incorrectas")
    })
    public ResponseEntity<Trabajador> login(@RequestBody LoginRequest request) {
        Trabajador trabajador = trabajadorService.obtenerTrabajadorPorEmail(request.getEmail());

        if (trabajador != null && passwordEncoder.matches(request.getPassword(), trabajador.getContraseña())) {
            return new ResponseEntity<>(trabajador, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
