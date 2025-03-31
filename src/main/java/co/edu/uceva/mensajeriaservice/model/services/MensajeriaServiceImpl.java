package co.edu.uceva.mensajeriaservice.model.services;

import co.edu.uceva.mensajeriaservice.model.entities.Mensajeria;
import co.edu.uceva.mensajeriaservice.model.repositories.IMensajeriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase que implementa los métodos de la interfaz IProductoService
 * para realizar las operaciones de negocio sobre la entidad Producto
 */


@Service
public class MensajeriaServiceImpl implements IMensajeriaService{

    IMensajeriaRepository mensajeriaRepository;

    public MensajeriaServiceImpl(IMensajeriaRepository mensajeriaRepository) {
        this.mensajeriaRepository = mensajeriaRepository;
    }

    @Override
    public Mensajeria save(Mensajeria mensajeria) {return mensajeriaRepository.save(mensajeria);}

    @Override
    public void delete(Long id) {mensajeriaRepository.deleteById(id);}

    @Override
    public void delete(Mensajeria mensajeria) {mensajeriaRepository.delete(mensajeria);}

    @Override
    public Mensajeria findById(Long id) {return mensajeriaRepository.findById(id).orElse(null);}

    @Override
    public Mensajeria update(Mensajeria mensajeria) {return mensajeriaRepository.save(mensajeria);}

    @Override
    public List<Mensajeria> findAll() {return (List<Mensajeria>) mensajeriaRepository.findAll();
    }
}
