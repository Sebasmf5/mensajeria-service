package co.edu.uceva.mensajeriaservice.domain.exception;

public class NoHayMensajesException extends RuntimeException {
    public NoHayMensajesException() {
        super("No hay mensajes en la base de datos");
    }
}
