package Controladores;

import Modelo.Producto;
import Modelo.Proveedor;
import Servicios.ProductoService;
import Servicios.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/usuarios")
public class ProveedorController {
    private final ProveedorService proveedorService;

    @Autowired
    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping
    public ResponseEntity<List<Proveedor>> getAllProveedores() {
        List<Proveedor> proveedores = proveedorService.findAll();
        return new ResponseEntity<>(proveedores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> getProveedor(@PathVariable String id) {
        Proveedor proveedor = proveedorService.findById(id);
        if (proveedor != null) {
            return new ResponseEntity<>(proveedor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Proveedor> createProveedor(@RequestBody Proveedor proveedor) {
        Proveedor newProveedor = proveedorService.save(proveedor);
        return new ResponseEntity<>(newProveedor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> updateProveedor(@PathVariable String id, @RequestBody Proveedor proveedor) {
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
    public ResponseEntity<Void> deleteProveedor(@PathVariable String id) {
        Proveedor existingProveedor = proveedorService.findById(id);
        if (existingProveedor != null) {
            proveedorService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Proveedor>> buscarProveedor(@RequestParam String nombre) {
        List<Proveedor> proveedores = proveedorService.buscarPorFiltros(nombre);
        return new ResponseEntity<>(proveedores, HttpStatus.OK);
    }

    @GetMapping("/auth")
    public ResponseEntity<Proveedor> getProveedorByToken(@RequestHeader("Authorization") String authToken) {
        Proveedor proveedor = proveedorService.findByAuthToken(authToken);
        if (proveedor != null) {
            return new ResponseEntity<>(proveedor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
