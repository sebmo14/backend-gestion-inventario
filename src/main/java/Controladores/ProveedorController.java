package Controladores;

import Modelo.Proveedor;
import Servicios.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@Tag(name = "Proveedores", description = "API para la gestión de proveedores")
public class ProveedorController {

    private final ProveedorService proveedorService;

    @Autowired
    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los proveedores", description = "Devuelve una lista con todos los proveedores registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de proveedores obtenida con éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<Proveedor>> getAllProveedores() {
        List<Proveedor> proveedores = proveedorService.findAll();
        return new ResponseEntity<>(proveedores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener proveedor por ID", description = "Devuelve los datos de un proveedor específico según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor encontrado"),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    })
    public ResponseEntity<Proveedor> getProveedor(
            @PathVariable @Parameter(description = "ID del proveedor") String id) {
        Proveedor proveedor = proveedorService.findById(id);
        return proveedor != null ?
                new ResponseEntity<>(proveedor, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo proveedor", description = "Registra un nuevo proveedor con los datos proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Proveedor creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<Proveedor> createProveedor(
            @RequestBody @Parameter(description = "Datos del proveedor a crear") Proveedor proveedor) {
        Proveedor newProveedor = proveedorService.save(proveedor);
        return new ResponseEntity<>(newProveedor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un proveedor", description = "Modifica los datos de un proveedor existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    })
    public ResponseEntity<Proveedor> updateProveedor(
            @PathVariable @Parameter(description = "ID del proveedor") String id,
            @RequestBody @Parameter(description = "Nuevos datos del proveedor") Proveedor proveedor) {
        Proveedor existingProveedor = proveedorService.findById(id);
        if (existingProveedor != null) {
            proveedor.setId(id);
            Proveedor updatedProveedor = proveedorService.update(proveedor);
            return new ResponseEntity<>(updatedProveedor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar proveedor", description = "Elimina un proveedor según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Proveedor eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    })
    public ResponseEntity<Void> deleteProveedor(
            @PathVariable @Parameter(description = "ID del proveedor") String id) {
        Proveedor existingProveedor = proveedorService.findById(id);
        if (existingProveedor != null) {
            proveedorService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar proveedor por nombre", description = "Busca proveedores cuyo nombre coincida (parcial o completo).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedores encontrados"),
            @ApiResponse(responseCode = "400", description = "Parámetros inválidos")
    })
    public ResponseEntity<List<Proveedor>> buscarProveedor(
            @RequestParam @Parameter(description = "Nombre del proveedor") String nombre) {
        List<Proveedor> proveedores = proveedorService.buscarPorFiltros(nombre);
        return new ResponseEntity<>(proveedores, HttpStatus.OK);
    }

    @GetMapping("/auth")
    @Operation(summary = "Obtener proveedor por token", description = "Devuelve los datos del proveedor autenticado mediante token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor autenticado encontrado"),
            @ApiResponse(responseCode = "401", description = "Token inválido o no autorizado")
    })
    public ResponseEntity<Proveedor> getProveedorByToken(
            @RequestHeader("Authorization") @Parameter(description = "Token de autorización") String authToken) {
        Proveedor proveedor = proveedorService.findByAuthToken(authToken);
        return proveedor != null ?
                new ResponseEntity<>(proveedor, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
