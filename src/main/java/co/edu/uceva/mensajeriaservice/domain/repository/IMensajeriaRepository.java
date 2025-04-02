package co.edu.uceva.mensajeriaservice.domain.model.repository;

import co.edu.uceva.mensajeriaservice.domain.model.Mensajeria;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Interface que hereda de CrudRepository para realizar las
 * operaciones de CRUD sobre la entidad Producto
 */
public interface IMensajeriaRepository extends JpaRepository<Mensajeria, Long> {
}
