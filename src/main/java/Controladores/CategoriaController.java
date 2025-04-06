package Controladores;

import Modelo.Categoria;
import Servicios.CategoriaService;
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
@RequestMapping("/api/categorias")
@Tag(name = "Categorías", description = "Operaciones relacionadas con las categorías de productos")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    @Operation(summary = "Obtener todas las categorías", description = "Devuelve una lista de todas las categorías registradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categorías obtenidas correctamente")
    })
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        List<Categoria> categorias = categoriaService.findAll();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una categoría por ID", description = "Devuelve los datos de una categoría usando su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría encontrada"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    public ResponseEntity<Categoria> getCategoriaById(
            @Parameter(description = "ID de la categoría a buscar", required = true)
            @PathVariable String id) {
        Categoria categoria = categoriaService.findById(id);
        return (categoria != null) ?
                new ResponseEntity<>(categoria, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva categoría", description = "Registra una nueva categoría en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoría creada correctamente")
    })
    public ResponseEntity<Categoria> createCategoria(
            @Parameter(description = "Objeto Categoría a crear", required = true)
            @RequestBody Categoria categoria) {
        Categoria newCategoria = categoriaService.save(categoria);
        return new ResponseEntity<>(newCategoria, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una categoría", description = "Actualiza la información de una categoría existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    public ResponseEntity<Categoria> updateCategoria(
            @Parameter(description = "ID de la categoría a actualizar", required = true)
            @PathVariable String id,
            @Parameter(description = "Objeto con la nueva información de la categoría", required = true)
            @RequestBody Categoria categoria) {
        Categoria existingCategoria = categoriaService.findById(id);
        if (existingCategoria != null) {
            categoria.setId(id);
            Categoria updatedCategoria = categoriaService.update(categoria);
            return new ResponseEntity<>(updatedCategoria, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una categoría", description = "Elimina una categoría del sistema dado su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoría eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    public ResponseEntity<Void> deleteCategoria(
            @Parameter(description = "ID de la categoría a eliminar", required = true)
            @PathVariable String id) {
        Categoria existingCategoria = categoriaService.findById(id);
        if (existingCategoria != null) {
            categoriaService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar categorías", description = "Busca categorías filtrando por nombre.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categorías encontradas")
    })
    public ResponseEntity<List<Categoria>> buscarCategoria(
            @Parameter(description = "Nombre de la categoría a buscar", required = true)
            @RequestParam String nombre) {
        List<Categoria> categorias = categoriaService.buscarPorFiltros(nombre);
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @GetMapping("/auth")
    @Operation(summary = "Obtener categoría por token", description = "Obtiene una categoría autorizada usando un token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría autorizada encontrada"),
            @ApiResponse(responseCode = "401", description = "Token inválido o no autorizado")
    })
    public ResponseEntity<Categoria> getCategoriaByToken(
            @Parameter(description = "Token de autorización en el encabezado", required = true)
            @RequestHeader("Authorization") String authToken) {
        Categoria categoria = categoriaService.findByAuthToken(authToken);
        return (categoria != null) ?
                new ResponseEntity<>(categoria, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
