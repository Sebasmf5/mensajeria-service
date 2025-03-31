package co.edu.uceva.mensajeriaservice.controllers;

import co.edu.uceva.mensajeriaservice.model.entities.Mensajeria;
import co.edu.uceva.mensajeriaservice.model.services.IMensajeriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/v1/mensajeria-service")
public class MensajeriaRestController {

    // Inyección de dependencia del servicio que proporciona servicios de CRUD
    private IMensajeriaService mensajeriaService;

    // Inyección de dependeencia del servicio que proporciona servicios de CRUD
    @Autowired
    public MensajeriaRestController(IMensajeriaService mensajeriaService) {this.mensajeriaService = mensajeriaService;}

    // Metodo que retorna todos los mensajes
    @GetMapping("/mensajeria")
    public List<Mensajeria> getMensajeria(){
        return mensajeriaService.findAll();
    }

    // Metodo que guarda un mensaje pasandolo por el cuerpo de la petición
    @PostMapping("/mensajeria")
    public Mensajeria save(@RequestBody Mensajeria mensajeria) {
        return mensajeriaService.save(mensajeria);
    }

    // Metodo que elimina un mensaje pasandolo por el cuerpo de la petición
    @DeleteMapping("/mensajeria")
    public void delete(@RequestBody Mensajeria mensajeria){
        mensajeriaService.delete(mensajeria);
    }

    // Metodo que actualiza un mensaje pasandolo por el cuerpo de la petición
    @PutMapping("/mensajeria")
    public Mensajeria update(@RequestBody Mensajeria mensajeria){
        return mensajeriaService.update(mensajeria);
    }

    // Metodo que retorna un mensaje por su id pasado por la URL
    @GetMapping("/mensajeria/{id}")
    public Mensajeria findById(@PathVariable("id") Long id){
        return mensajeriaService.findById(id);
    }
}

