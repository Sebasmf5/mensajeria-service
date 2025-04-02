package co.edu.uceva.mensajeriaservice.domain.model.service;

import co.edu.uceva.mensajeriaservice.domain.model.Mensajeria;
import co.edu.uceva.mensajeriaservice.domain.model.repository.IMensajeriaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase que implementa los métodos de la interfaz IProductoService
 * para realizar las operaciones de negocio sobre la entidad Producto
 */


@Service
public class MensajeriaServiceImpl implements IMensajeriaService{

    IMensajeriaRepository mensajeriaRepository;

    public MensajeriaServiceImpl(IMensajeriaRepository mensajeriaRepository) {this.mensajeriaRepository = mensajeriaRepository;}

    @Override
    @Transactional
    public Mensajeria save(Mensajeria mensajeria) {return mensajeriaRepository.save(mensajeria);}

    @Override
    @Transactional
    public void delete(Long id) {mensajeriaRepository.deleteById(id);}

    @Override
    public void delete(Mensajeria mensajeria) {mensajeriaRepository.delete(mensajeria);}

    @Override
    @Transactional(readOnly = true)
    public Mensajeria findById(Long id) {return mensajeriaRepository.findById(id).orElse(null);}

    @Override
    @Transactional
    public Mensajeria update(Mensajeria mensajeria) {return mensajeriaRepository.save(mensajeria);}

    @Override
    @Transactional(readOnly = true)
    public List<Mensajeria> findAll() {return (List<Mensajeria>) mensajeriaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Mensajeria> findAll(Pageable pageable) {
        return mensajeriaRepository.findAll(pageable);
    }
}
