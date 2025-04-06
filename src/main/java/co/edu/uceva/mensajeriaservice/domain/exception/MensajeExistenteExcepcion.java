package co.edu.uceva.mensajeriaservice.domain.exception;

public class MensajeExistenteExcepcion extends RuntimeException {
    public MensajeExistenteExcepcion(String mensaje) {
        super("El mensaje con asunto: " + mensaje + " ya existe");
    }
}
