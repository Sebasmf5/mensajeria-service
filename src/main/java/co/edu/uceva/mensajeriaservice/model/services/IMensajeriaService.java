package co.edu.uceva.mensajeriaservice.model.services;

import co.edu.uceva.mensajeriaservice.model.entities.Mensajeria;

import java.util.List;

/**
 * Interface que define los métodos que se pueden realizar sobre la entidad Producto
 */
public interface IMensajeriaService {
    Mensajeria save(Mensajeria mensajeria);
    void delete(Long id);
    void delete(Mensajeria mensajeria);
    Mensajeria findById(Long id);
    Mensajeria update(Mensajeria mensajeria);
    List<Mensajeria> findAll();
}
