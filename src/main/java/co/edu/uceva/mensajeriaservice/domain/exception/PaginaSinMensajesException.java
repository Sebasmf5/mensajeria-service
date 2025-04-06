package co.edu.uceva.mensajeriaservice.domain.exception;

public class PaginaSinMensajesException extends RuntimeException {
  public PaginaSinMensajesException(int page) {
    super("No hay mensajes en la pagina solicitada: " + page);
  }
}
