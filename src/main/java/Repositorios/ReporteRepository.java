package Repositorios;

import Modelo.Reporte;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository

public class ReporteRepository {
    private final List<Reporte> baseDeDatos = new ArrayList<>();
    private final List<String> authTokens = new ArrayList<>();

    public Reporte save(Reporte reporte) {
        baseDeDatos.add(reporte);
        authTokens.add(reporte.getId());
        return reporte;
    }

    public Reporte findById(String id) {
        for (Reporte reporte : baseDeDatos) {
            if (reporte.getId().equals(id)) {
                return reporte;
            }
        }
        return null;
    }

    public List<Reporte> findAll() {
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

    public Reporte update(Reporte reporte) {
        for (int i = 0; i < baseDeDatos.size(); i++) {
            if (baseDeDatos.get(i).getId().equals(reporte.getId())) {
                baseDeDatos.set(i, reporte);
                return reporte;
            }
        }
        return null;
    }

    public List<Reporte> buscarPorFiltros(String nombre) {
        List<Reporte> resultado = new ArrayList<>();
        for (Reporte reporte : baseDeDatos) {
            boolean coincideNombre = (nombre == null || reporte.getNombre().contains(nombre));
            if (coincideNombre) {
                resultado.add(reporte);
            }
        }
        return resultado;
    }

    public Reporte findByAuthToken(String authToken) {
        for (String token : authTokens) {
            if (token.equals(authToken)) {
                for (Reporte reporte : baseDeDatos) {
                    if (reporte.getId().equals(token)) {
                        return reporte;
                    }
                }
            }
        }
        return null;
    }

}
