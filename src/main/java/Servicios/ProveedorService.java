package Servicios;

import Modelo.Proveedor;
import Repositorios.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service

public class ProveedorService {

    private final ProveedorRepository proveedorRepository;


    @Autowired
    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
        //initSampleData();
    }




    private void initSampleData() {
        Proveedor proveedor1 = new Proveedor("Tech Supplies Inc.", "contacto@techsupplies.com", "Calle 123, Ciudad A", 555123456);
        Proveedor proveedor2 = new Proveedor("ElectroMundo S.A.", "ventas@electromundo.com", "Av. Industrial 45, Ciudad B", 555987654);
        Proveedor proveedor3 = new Proveedor("Muebles y Estilo", "info@mueblesyestilo.com", "Carrera 78, Ciudad C", 555654321);
        Proveedor proveedor4 = new Proveedor("Moda Express", "atencion@modaexpress.com", "Plaza Central, Ciudad D", 555789123);

        save(proveedor1);
        save(proveedor2);
        save(proveedor3);
        save(proveedor4);
    }


    public Proveedor save(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public Proveedor findById(String id) {
        return proveedorRepository.findById(id);
    }

    public List<Proveedor> findAll() {
        return proveedorRepository.findAll();
    }

    public Proveedor update(Proveedor proveedor) {
        return proveedorRepository.update(proveedor);
    }

    public void deleteById(String id) {
        proveedorRepository.deleteById(id);
    }

    public List<Proveedor> buscarPorFiltros(String nombre) {
        return proveedorRepository.buscarPorFiltros(nombre);
    }

    public Proveedor findByAuthToken(String authToken) {
        return proveedorRepository.findByAuthToken(authToken);
    }
}
