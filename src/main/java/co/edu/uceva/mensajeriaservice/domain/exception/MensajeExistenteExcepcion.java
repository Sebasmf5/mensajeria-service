package co.edu.uceva.mensajeriaservice.domain.exception;

public class MensajeExistenteExcepcion extends RuntimeException {
    public MensajeExistenteExcepcion(Long id) {
        super("El mensaje con id: " + id + " ya existe");
    }
}
