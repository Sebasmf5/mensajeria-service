package co.edu.uceva.mensajeriaservice.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mensajeria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El cuerpo del mensaje no puede estar vacío")
    @Size(min = 10, max = 2000, message = "El mensaje debe tener entre 10 y 2000 caracteres")
    @Column(nullable = false, length = 2000)
    private String asunto;

    @NotBlank(message = "El correo del destinatario es obligatorio")
    @Email(message = "El correo debe tener un formato válido (ej: usuario@dominio.com)")
    @Column(nullable = false, length = 100)
    private String correo_destinatario;

    @NotBlank(message = "El cuerpo del mensaje no puede estar vacío")
    @Size(min = 10, max = 2000, message = "El mensaje debe tener entre 10 y 2000 caracteres")
    @Column(nullable = false, length = 2000)
    private String cuerpo_correo;

    @NotNull(message = "La fecha de envío no puede ser nula")
    @Column(nullable = false)
    private LocalDate fecha_envio;
}
