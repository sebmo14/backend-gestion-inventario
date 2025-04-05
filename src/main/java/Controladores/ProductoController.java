package Controladores;

import Modelo.Categoria;
import Modelo.Producto;
import Servicios.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/obtener")
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productoService.findAll();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable String id) {
        Producto producto = productoService.findById(id);
        if (producto != null) {
            return new ResponseEntity<>(producto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        Producto newProducto = productoService.save(producto);
        return new ResponseEntity<>(newProducto, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable String id, @RequestBody Producto producto) {
        Producto existingProducto = productoService.findById(id);
        if (existingProducto != null) {
            producto.setId(id);
            Producto updatedProducto = productoService.update(producto);
            return new ResponseEntity<>(updatedProducto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable String id) {
        Producto existingProducto = productoService.findById(id);
        if (existingProducto != null) {
            productoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Producto>> buscarProducto(
            @RequestParam String nombre,
            @RequestParam(required = false) String categoria) {
        List<Producto> productos = productoService.buscarPorFiltros(nombre, categoria);
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @GetMapping("/auth")
    public ResponseEntity<Producto> getProductoByToken(@RequestHeader("Authorization") String authToken) {
        Producto producto = productoService.findByAuthToken(authToken);
        if (producto != null) {
            return new ResponseEntity<>(producto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
