package Repositorios;

import org.springframework.stereotype.Repository;
import Modelo.Categoria;
import java.util.ArrayList;
import java.util.List;

@Repository

public class CategoriaRepository {
    private final List<Categoria> baseDeDatos = new ArrayList<>();
    private final List<String> authTokens = new ArrayList<>();

    public Categoria save(Categoria categoria) {
        baseDeDatos.add(categoria);
        authTokens.add(categoria.getId());
        return categoria;
    }

    public Categoria findById(String id) {
        for (Categoria categoria : baseDeDatos) {
            if (categoria.getId().equals(id)) {
                return categoria;
            }
        }
        return null;
    }

    public List<Categoria> findAll() {
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

    public Categoria update(Categoria categoria) {
        for (int i = 0; i < baseDeDatos.size(); i++) {
            if (baseDeDatos.get(i).getId().equals(categoria.getId())) {
                baseDeDatos.set(i, categoria);
                return categoria;
            }
        }
        return null;
    }

    public List<Categoria> buscarPorFiltros(String nombre) {
        List<Categoria> resultado = new ArrayList<>();
        for (Categoria categoria : baseDeDatos) {
            boolean coincideNombre = (nombre == null || categoria.getNombre().contains(nombre));
            //boolean coincideCategoria = (categoria == null || categoria.get().getNombre().contains(categoria));
            if (coincideNombre) {
                resultado.add(categoria);
            }
        }
        return resultado;
    }

    public Categoria findByAuthToken(String authToken) {
        for (String token : authTokens) {
            if (token.equals(authToken)) {
                for (Categoria categoria : baseDeDatos) {
                    if (categoria.getId().equals(token)) {
                        return categoria;
                    }
                }
            }
        }
        return null;
    }

}