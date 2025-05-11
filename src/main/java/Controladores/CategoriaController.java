package Controladores;

import Modelo.Categoria;
import Servicios.CategoriaService;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
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
        List<Categoria> categorias = categoriaService.obtenerTodasLasCategorias();
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
            @PathVariable int id) {
        Categoria categoria = categoriaService.obtenerCategoria(id);
        return (categoria != null) ?
                new ResponseEntity<>(categoria, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/buscar")
    @Operation(summary = "Buscar categoría por nombre", description = "Devuelve una categoría cuyo nombre coincida con el proporcionado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría encontrada"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    public ResponseEntity<Categoria> getCategoriaByNombre(
            @Parameter(description = "Nombre de la categoría a buscar", required = true)
            @RequestParam String nombre) {
        Categoria categoria = categoriaService.obtenerCategoriaPorNombre(nombre);
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
        Categoria newCategoria = categoriaService.guardarCategoria(categoria);
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
            @PathVariable Integer id,
            @Parameter(description = "Objeto con la nueva información de la categoría", required = true)
            @RequestBody Categoria categoria) {
        Categoria existingCategoria = categoriaService.obtenerCategoria(id);
        if (existingCategoria != null) {
            categoria.setId(id);
            Categoria updatedCategoria = categoriaService.guardarCategoria(categoria);
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
            @PathVariable Integer id) {
        Categoria existingCategoria = categoriaService.obtenerCategoria(id);
        if (existingCategoria != null) {
            categoriaService.eliminarCategoria(existingCategoria);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
