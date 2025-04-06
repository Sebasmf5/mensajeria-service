package co.edu.uceva.mensajeriaservice.domain.exception;

public class MensajeNoEncontradoExcepcion extends RuntimeException {
    public MensajeNoEncontradoExcepcion(Long id) {
        super("El mensaje con id: " + id + " no fue encontrado");
    }
}
