package co.edu.uceva.mensajeriaservice.model.repositories;

import co.edu.uceva.mensajeriaservice.model.entities.Mensajeria;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Interface que hereda de CrudRepository para realizar las
 * operaciones de CRUD sobre la entidad Producto
 */
public interface IMensajeriaRepository extends JpaRepository<Mensajeria, Long> {
}
