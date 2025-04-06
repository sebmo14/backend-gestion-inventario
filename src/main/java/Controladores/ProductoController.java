package Controladores;

import Modelo.Categoria;
import Modelo.Producto;
import Servicios.ProductoService;
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
@RequestMapping("/api/productos")
@Tag(name = "Productos", description = "Operaciones relacionadas con los productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los productos", description = "Devuelve una lista de todos los productos disponibles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de productos obtenida correctamente")
    })
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productoService.findAll();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un producto por ID", description = "Devuelve los detalles de un producto dado su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<Producto> getProductoById(
            @Parameter(description = "ID del producto que se desea obtener", required = true)
            @PathVariable String id) {
        Producto producto = productoService.findById(id);
        return (producto != null) ? new ResponseEntity<>(producto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo producto", description = "Crea y registra un nuevo producto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado correctamente")
    })
    public ResponseEntity<Producto> createProducto(
            @Parameter(description = "Objeto Producto que se desea crear", required = true)
            @RequestBody Producto producto) {

        Producto newProducto = productoService.save(producto);
        return new ResponseEntity<>(newProducto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un producto", description = "Actualiza los datos de un producto existente usando su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<Producto> updateProducto(
            @Parameter(description = "ID del producto a actualizar", required = true)
            @PathVariable String id,
            @Parameter(description = "Objeto Producto con la nueva información", required = true)
            @RequestBody Producto producto) {
        Producto existingProducto = productoService.findById(id);
        if (existingProducto != null) {
            producto.setId(id);
            Producto updatedProducto = productoService.update(producto);
            return new ResponseEntity<>(updatedProducto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un producto", description = "Elimina un producto dado su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<Void> deleteProducto(
            @Parameter(description = "ID del producto que se desea eliminar", required = true)
            @PathVariable String id) {
        Producto existingProducto = productoService.findById(id);
        if (existingProducto != null) {
            productoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar productos", description = "Busca productos por nombre y/o categoría.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Productos encontrados")
    })
    public ResponseEntity<List<Producto>> buscarProducto(
            @Parameter(description = "Nombre del producto a buscar", required = true)
            @RequestParam String nombre,
            @Parameter(description = "Categoría del producto (opcional)")
            @RequestParam(required = false) String categoria) {
        List<Producto> productos = productoService.buscarPorFiltros(nombre, categoria);
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @GetMapping("/auth")
    @Operation(summary = "Obtener producto por token", description = "Obtiene un producto asociado a un token de autorización.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto autorizado encontrado"),
            @ApiResponse(responseCode = "401", description = "Token inválido o no autorizado")
    })
    public ResponseEntity<Producto> getProductoByToken(
            @Parameter(description = "Token de autorización en el encabezado", required = true)
            @RequestHeader("Authorization") String authToken) {
        Producto producto = productoService.findByAuthToken(authToken);
        return (producto != null) ? new ResponseEntity<>(producto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
