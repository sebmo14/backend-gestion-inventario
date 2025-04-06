package Repositorios;

import Modelo.Categoria;
import Modelo.Producto;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository

public class ProductoRepository {
    private final List<Producto> baseDeDatos = new ArrayList<>();
    private final List<String> authTokens = new ArrayList<>();

    private static boolean initialized = false;
    @PostConstruct
    public void init() {
        if (!initialized) {
            System.out.println("Inicializando repositorio de productos");

            initialized = true;
        }
    }


    public Producto save(Producto producto) {
        baseDeDatos.add(producto);
        authTokens.add(producto.getId());
        return producto;
    }

    public Producto findById(String id) {
        for (Producto producto : baseDeDatos) {
            if (producto.getId().equals(id)) {
                return producto;
            }
        }
        return null;
    }

    public List<Producto> findAll() {
        return new ArrayList<>(baseDeDatos);
    }

    public void deleteById(String id) {
        for (int i = 0; i < baseDeDatos.size(); i++) {
            if (baseDeDatos.get(i).getId().equals(id)) {
                baseDeDatos.remove(i);
                return;
            }
        }
    }

    public Producto update(Producto producto) {
        for (int i = 0; i < baseDeDatos.size(); i++) {
            if (baseDeDatos.get(i).getId().equals(producto.getId())) {
                baseDeDatos.set(i, producto);
                return producto;
            }
        }
        return null;
    }

    public List<Producto> buscarPorFiltros(String nombre, String categoria) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto producto : baseDeDatos) {
            boolean coincideNombre = (nombre == null || producto.getNombre().contains(nombre));
            boolean coincideCategoria = (categoria == null || producto.getCategoria().getNombre().contains(categoria));
            if (coincideNombre && coincideCategoria) {
                resultado.add(producto);
            }
        }
        return resultado;
    }

    public Producto findByAuthToken(String authToken) {
        for (String token : authTokens) {
            if (token.equals(authToken)) {
                for (Producto producto : baseDeDatos) {
                    if (producto.getId().equals(token)) {
                        return producto;
                    }
                }
            }
        }
        return null;
    }

}
