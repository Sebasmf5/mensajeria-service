package co.edu.uceva.mensajeriaservice.delivey.rest;

import co.edu.uceva.mensajeriaservice.domain.exception.MensajeNoEncontradoExcepcion;
import co.edu.uceva.mensajeriaservice.domain.exception.NoHayMensajesException;
import co.edu.uceva.mensajeriaservice.domain.exception.PaginaSinMensajesException;
import co.edu.uceva.mensajeriaservice.domain.exception.ValidationException;
import co.edu.uceva.mensajeriaservice.domain.model.Mensajeria;
import co.edu.uceva.mensajeriaservice.domain.model.service.IMensajeriaService;
import jakarta.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.stream;

@RestController
@RequestMapping ("/api/v1/mensajeria-service")
public class MensajeriaRestController {

    // Inyección de dependencia del servicio que proporciona servicios de CRUD
    private final IMensajeriaService mensajeriaService;

    private static final String MENSAJE = "mensaje";
    private static final String MENSAJE_UNICO = "mensajeUnico";
    private static final String MENSAJES = "mensajes";

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
        List<Mensajeria> mensajes = mensajeriaService.findAll();
        if(mensajes.isEmpty()){ throw new NoHayMensajesException();
        }
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJES, mensajes);
        return ResponseEntity.ok(response);
    }

    /*
    * listar mensajes con paginacion
    * */
    // Metodo que retorna todos los mensajes paginados
    @GetMapping("/mensajeria/page/{page}")
    public ResponseEntity<Object> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        Page<Mensajeria> mensajes = mensajeriaService.findAll(pageable);
        if(mensajes.isEmpty()){ throw new PaginaSinMensajesException(page);}
        return ResponseEntity.ok(mensajes);
    }

    /*
    * Crear un nuevo mensaje pasando el objeto en el cuerpo de la peticion
    * */
    @PostMapping("/mensajeria")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody Mensajeria mensajeria, BindingResult result) {
        if(result.hasErrors()){
            throw new ValidationException(result);
        }
        Map<String, Object> response = new HashMap<>();
        Mensajeria nuevoMensaje = mensajeriaService.save(mensajeria);
        response.put(MENSAJE, "El mensaje ha sido creado con éxito!");
        response.put(MENSAJES, nuevoMensaje);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    /*
    * Eliminar un producto psando el objeto en el cuerpo de la petición
    * */
    @DeleteMapping("/mensajeria")
    public ResponseEntity<Map<String, Object>> delete(@RequestBody Mensajeria mensaje){
        mensajeriaService.findById(mensaje.getId())
                .orElseThrow(() -> new MensajeNoEncontradoExcepcion(mensaje.getId()));
        mensajeriaService.delete(mensaje);
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, "El mensaje ha sido eliminado con exito!");
        response.put(MENSAJE_UNICO, null);
        return ResponseEntity.ok(response);
    }

    /*
    * Actualizar un ,emsaje pasando el objeto en el cuerpo de la petición.
    *  @param mensaje: Objeto Mensajeria que se va a actualizar */
    @PutMapping("/mensajeria")
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody Mensajeria mensaje, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }
        mensajeriaService.findById(mensaje.getId())
                .orElseThrow(() -> new MensajeNoEncontradoExcepcion(mensaje.getId()));
        Map<String, Object> response = new HashMap<>();
        Mensajeria mensajeActualizado = mensajeriaService.update(mensaje);
        response.put(MENSAJE, "El mensaje ha sido actualizado con éxito!");
        response.put(MENSAJE_UNICO, mensajeActualizado);
        return ResponseEntity.ok(response);
    }

    /*
    * Obtener un mensaje por su ID
    * */
    @GetMapping("/mensajeria/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id){
        Mensajeria mensaje = mensajeriaService.findById(id)
                .orElseThrow(() -> new MensajeNoEncontradoExcepcion(id));
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, "El mensaje ha sido encontrado con exito");
        response.put(MENSAJE_UNICO, mensaje);
        return ResponseEntity.ok(response);
    }
}

