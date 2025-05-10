package Servicios;

import Modelo.Proveedor;
import Repositorios.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    // Guardar o actualizar un proveedor
    public Proveedor guardarProveedor(Proveedor proveedor) {
        proveedorRepository.save(proveedor);
        return proveedor;
    }

    // Obtener un proveedor por su id
    public Proveedor obtenerProveedor(String id) {
        return proveedorRepository.findById(id);
    }

    // Obtener todos los proveedores
    public List<Proveedor> obtenerTodosLosProveedores() {
        return proveedorRepository.findAll();
    }

    // Eliminar un proveedor
    public void eliminarProveedor(Proveedor proveedor) {
        proveedorRepository.delete(proveedor);
    }
}
