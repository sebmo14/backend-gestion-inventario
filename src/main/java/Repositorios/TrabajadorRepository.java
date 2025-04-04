//NO NECESARIO?
//TODO
package Repositorios;

import Modelo.Trabajador;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository

public class TrabajadorRepository {
    private final List<Trabajador> baseDeDatos = new ArrayList<>();
    private final List<String> authTokens = new ArrayList<>();

    public Trabajador save(Trabajador trabajador) {
        baseDeDatos.add(trabajador);
        authTokens.add(trabajador.getId());
        return trabajador;
    }

    public Trabajador findById(String id) {
        for (Trabajador trabajador : baseDeDatos) {
            if (trabajador.getId().equals(id)) {
                return trabajador;
            }
        }
        return null;
    }

    public List<Trabajador> findAll() {
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

    public Trabajador update(Trabajador trabajador) {
        for (int i = 0; i < baseDeDatos.size(); i++) {
            if (baseDeDatos.get(i).getId().equals(trabajador.getId())) {
                baseDeDatos.set(i, trabajador);
                return trabajador;
            }
        }
        return null;
    }

    public List<Trabajador> buscarPorFiltros(String nombre) {
        List<Trabajador> resultado = new ArrayList<>();
        for (Trabajador trabajador : baseDeDatos) {
            boolean coincideNombre = (nombre == null || trabajador.getNombre().contains(nombre));
            if (coincideNombre) {
                resultado.add(trabajador);
            }
        }
        return resultado;
    }

    public Trabajador findByAuthToken(String authToken) {
        for (String token : authTokens) {
            if (token.equals(authToken)) {
                for (Trabajador trabajador : baseDeDatos) {
                    if (trabajador.getId().equals(token)) {
                        return trabajador;
                    }
                }
            }
        }
        return null;
    }

}