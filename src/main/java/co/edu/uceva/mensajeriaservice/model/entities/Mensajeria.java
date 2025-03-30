package co.edu.uceva.mensajeriaservice.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/*
*
long id;
string correo;
string correoDestinatario;
string titulo;
string cuerpoMensaje;
* */
@Entity //Mapea la clase como una entidad que corresponde a una tabla en la base de dato
@Getter
@Setter
public class Mensajeria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String correo;
    private String correoDestinatario;
    private String titulo;
    private String cuerpoMensaje;

}
