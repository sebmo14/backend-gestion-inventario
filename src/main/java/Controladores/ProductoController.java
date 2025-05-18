package Controladores;

import Modelo.Categoria;
import Modelo.Producto;
import Servicios.JwtService;
import Servicios.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/productos")
@Tag(name = "Productos", description = "Operaciones relacionadas con los productos")
public class ProductoController {

    private final ProductoService productoService;
    private final JwtService jwtService;

    @Autowired
    public ProductoController(ProductoService productoService, JwtService jwtService) {
        this.productoService = productoService;
        this.jwtService = jwtService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los productos", description = "Devuelve una lista de todos los productos disponibles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de productos obtenida correctamente")
    })
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productoService.obtenerTodosLosProductos();
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
            @PathVariable Integer id) {
        Producto producto = productoService.obtenerProducto(id);
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

        Producto newProducto = productoService.guardarProducto(producto);
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
            @PathVariable Integer id,
            @Parameter(description = "Objeto Producto con la nueva información", required = true)
            @RequestBody Producto producto) {
        Producto existingProducto = productoService.obtenerProducto(id);
        if (existingProducto != null) {
            producto.setId(id);
            Producto updatedProducto = productoService.guardarProducto(producto);
            return new ResponseEntity<>(updatedProducto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un producto", description = "Elimina un producto dado su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
            @ApiResponse(responseCode = "401", description = "No autorizado") // <- añadido
    })
    public ResponseEntity<Void> deleteProducto(
            @Parameter(description = "ID del producto que se desea eliminar", required = true)
            @PathVariable Integer id,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        // Extraer el token desde el header
        String token = jwtService.extractToken(authHeader);

        // Validar el token
        if (token == null || !jwtService.validateJwtToken(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // Si el token es válido, continuar con la lógica
        Producto existingProducto = productoService.obtenerProducto(id);
        if (existingProducto != null) {
            productoService.eliminarProducto(existingProducto);
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
        List<Producto> productos = productoService.obtenerProductosPorFiltro(nombre, categoria);
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }
}
