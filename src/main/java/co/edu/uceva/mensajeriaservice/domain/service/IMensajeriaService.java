package co.edu.uceva.mensajeriaservice.domain.model.service;

import co.edu.uceva.mensajeriaservice.domain.model.Mensajeria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Interface que define los métodos que se pueden realizar sobre la entidad Producto
 */
public interface IMensajeriaService {
    Mensajeria save(Mensajeria mensajeria);
    void delete(Long id);
    void delete(Mensajeria mensajeria);
    Optional<Mensajeria> findById(Long id);
    Mensajeria update(Mensajeria mensajeria);
    List<Mensajeria> findAll();
    Page<Mensajeria> findAll(Pageable pageable);
}
