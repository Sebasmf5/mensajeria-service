package co.edu.uceva.mensajeriaservice.controllers;

import co.edu.uceva.mensajeriaservice.model.entities.Mensajeria;
import co.edu.uceva.mensajeriaservice.model.services.IMensajeriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping ("/api/v1/mensajeria-service")
public class MensajeriaRestController {

    // Inyección de dependencia del servicio que proporciona servicios de CRUD
    private final IMensajeriaService mensajeriaService;

    private static final String ERROR = "error";
    private static final String MENSAJE = "mensaje";
    private static final String MENSAJERIA = "mensajeria";
    private static final String MENSAJERIAS = "mensajerias";

    // Inyección de dependeencia del servicio que proporciona servicios de CRUD
    public MensajeriaRestController(IMensajeriaService mensajeriaService) {
        this.mensajeriaService = mensajeriaService;
    }

    /*
    * Listar todos los productos
    * */
    // Metodo que retorna todos los mensajes
    @GetMapping("/mensajerias")
    public ResponseEntity<Map<String, Object>> getMensajes(){
        Map<String, Object> response = new HashMap<>();

        try{
            List<Mensajeria> mensajerias = mensajeriaService.findAll();

            if(mensajerias.isEmpty()){
                response.put(MENSAJE, "No hay mensajes en la base de datos");
                response.put(MENSAJERIAS, mensajerias); // para que siempre el mismo campo
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            response.put(MENSAJERIAS, mensajerias);
            return ResponseEntity.ok(response);
        }catch (DataAccessException e){
            response.put(MENSAJE, "Error al consultar la base de datos");
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /*
    * listar productos con paginacion
    * */
    // Metodo que retorna todos los mensajes paginados
    @GetMapping("/mensajeria/page/{page}")
    public ResponseEntity<Object> index(@PathVariable Integer page) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable = PageRequest.of(page, 4);

        try {
            Page<Mensajeria> mensajerias = mensajeriaService.findAll(pageable);

            if(mensajerias.isEmpty()){
                response.put(MENSAJE, "No hay mensajes en la página solicitada");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            return ResponseEntity.ok(mensajerias);
        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al consultar la base de datos");
            response.put(ERROR, e.getMessage().concat(": ").concat(":").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }catch (IllegalArgumentException e){
            response.put(MENSAJE, "Número de página inválido.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    /*
    * Crear un nuevo mensaje pasando el objeto en el cuerpo de la peticion
    * */
    @PostMapping("/mensajeria")
    public ResponseEntity<Map<String, Object>> save(@RequestBody Mensajeria mensajeria) {
        Map<String, Object> response = new HashMap<>();

        try{
            //Guardar el mensaje en la base de datos
            Mensajeria nuevoMensaje = mensajeriaService.save(mensajeria);

            response.put(MENSAJE, "El mensaje ha sido creado correctamente");
            response.put(MENSAJERIAS, nuevoMensaje);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (DataAccessException e){
            response.put(MENSAJE, "Error al insertar el mensaje en la base de datos.");
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    /*
    * Eliminar un producto psando el objeto en el cuerpo de la petición
    * */
    @DeleteMapping("/mensajeria")
    public ResponseEntity<Map<String, Object>> delete(@RequestBody Mensajeria mensajeria){
        Map<String, Object> response = new HashMap<>();
        try {
            Mensajeria mensajeExistente = mensajeriaService.findById(mensajeria.getId());
            if(mensajeExistente == null){
                response.put(MENSAJE, "El mensaje id: " + mensajeria.getId() + " no existe");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            mensajeriaService.delete(mensajeria);
            response.put(MENSAJE, "El producto ha sido eliminado con éxito!");
            return ResponseEntity.ok(response);
        } catch (DataAccessException e){
            response.put(MENSAJE, "Erro al eliminar el mensaje de la base de datos");
            response.put(ERROR,  e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    /*
    * Actualizar un ,emsaje pasando el objeto en el cuerpo de la petición.
    *  @param mensaje: Objeto Mensajeria que se va a actualizar */
    @PutMapping("/mensajeria")
    public ResponseEntity<Map<String, Object>> update(@RequestBody Mensajeria mensajeria){
        Map<String, Object> response = new HashMap<>();

        try{
            //Verificar si el mensaje existe antes de actualizar
            if(mensajeriaService.findById(mensajeria.getId()) == null){
                response.put(MENSAJE, "Error: No se pudo editar el mensaje ID: " + mensajeria.getId());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            //Guardar directamente el producto actualizado en la base de datos
            Mensajeria mensajeActualizado = mensajeriaService.save(mensajeria);
            response.put(MENSAJE, "El mensaje ha sido actualizado con exito!");
            response.put(MENSAJERIA, mensajeActualizado);
            return ResponseEntity.ok(response);
        } catch (DataAccessException e){
             response.put(MENSAJE, "Erro al actualizar el mensaje de la base de datos");
             response.put(ERROR,  e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /*
    * Obtener un mensaje por su ID
    * */
    @GetMapping("/mensajeria/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable("id") Long id){
       Map<String, Object> response = new HashMap<>();

       try {
           Mensajeria mensaje = mensajeriaService.findById(id);

           if(mensaje == null){
               response.put(MENSAJE, "El mensaje id: " + id + " no existe en la base de datos");
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
           }

           response.put(MENSAJE, "El producto ha actualizado con exito!");
           response.put(MENSAJERIA, mensaje);
           return ResponseEntity.ok(response);
       } catch (DataAccessException e){
           response.put(MENSAJE, "Error al consultar la base de datos");
           response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
       }
    }
}

