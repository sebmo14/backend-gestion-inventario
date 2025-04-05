package Controladores;

import Modelo.Categoria;
import Servicios.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/usuarios")
public class CategoriaController {
    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        List<Categoria> categorias = categoriaService.findAll();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable String id) {
        Categoria categoria = categoriaService.findById(id);
        if (categoria != null) {
            return new ResponseEntity<>(categoria, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Categoria> createUsuario(@RequestBody Categoria categoria) {
        Categoria newCategoria = categoriaService.save(categoria);
        return new ResponseEntity<>(newCategoria, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateUsuario(@PathVariable String id, @RequestBody Categoria categoria) {
        Categoria existingUsuario = categoriaService.findById(id);
        if (existingUsuario != null) {
            categoria.setId(id);
            Categoria updatedCategoria = categoriaService.update(categoria);
            return new ResponseEntity<>(updatedCategoria, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable String id) {
        Categoria existingCategoria = categoriaService.findById(id);
        if (existingCategoria != null) {
            categoriaService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Categoria>> buscarCategoria(@RequestParam String nombre) {
        List<Categoria> categorias = categoriaService.buscarPorFiltros(nombre);
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @GetMapping("/auth")
    public ResponseEntity<Categoria> getUserByToken(@RequestHeader("Authorization") String authToken) {
        Categoria categoria = categoriaService.findByAuthToken(authToken);
        if (categoria != null) {
            return new ResponseEntity<>(categoria, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
