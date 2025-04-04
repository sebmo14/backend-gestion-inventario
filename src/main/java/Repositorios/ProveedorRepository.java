package Repositorios;

import Modelo.Proveedor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository

public class ProveedorRepository {
    private final List<Proveedor> baseDeDatos = new ArrayList<>();
    private final List<String> authTokens = new ArrayList<>();

    public Proveedor save(Proveedor proveedor) {
        baseDeDatos.add(proveedor);
        authTokens.add(proveedor.getId());
        return proveedor;
    }

    public Proveedor findById(String id) {
        for (Proveedor proveedor : baseDeDatos) {
            if (proveedor.getId().equals(id)) {
                return proveedor;
            }
        }
        return null;
    }

    public List<Proveedor> findAll() {
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

    public Proveedor update(Proveedor proveedor) {
        for (int i = 0; i < baseDeDatos.size(); i++) {
            if (baseDeDatos.get(i).getId().equals(proveedor.getId())) {
                baseDeDatos.set(i, proveedor);
                return proveedor;
            }
        }
        return null;
    }

    public List<Proveedor> buscarPorFiltros(String nombre) {
        List<Proveedor> resultado = new ArrayList<>();
        for (Proveedor proveedor : baseDeDatos) {
            boolean coincideNombre = (nombre == null || proveedor.getNombre().contains(nombre));
            if (coincideNombre) {
                resultado.add(proveedor);
            }
        }
        return resultado;
    }

    public Proveedor findByAuthToken(String authToken) {
        for (String token : authTokens) {
            if (token.equals(authToken)) {
                for (Proveedor proveedor : baseDeDatos) {
                    if (proveedor.getId().equals(token)) {
                        return proveedor;
                    }
                }
            }
        }
        return null;
    }

}
