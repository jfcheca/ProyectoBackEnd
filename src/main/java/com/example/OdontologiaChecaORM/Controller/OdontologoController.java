package com.example.OdontologiaChecaORM.Controller;

import com.example.OdontologiaChecaORM.domain.Odontologo;
import com.example.OdontologiaChecaORM.exceptions.ResourceNotFoundException;
import com.example.OdontologiaChecaORM.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

    @RestController
    @RequestMapping("/odontologos")

public class OdontologoController {

    private OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(id);
        if (odontologoBuscado.isPresent()) {
            return ResponseEntity.ok(odontologoBuscado.get());

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //BUSCAR TODOS

    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarTodosLosOdontologos(){
        return ResponseEntity.ok(odontologoService.buscarOdontologos());
    }

    //AGREGAR

    @PostMapping
    public ResponseEntity<Odontologo> agregarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.crearOdontologo(odontologo));
    }


    //ACTUALIZAR

        @PutMapping
        public  ResponseEntity<Odontologo> actualizarOdontologo (@RequestBody Odontologo odontologo) throws ResourceNotFoundException {
            if(odontologoService.buscarOdontologo(odontologo.getId())!=null){
                return ResponseEntity.ok(odontologoService.actualizarOdontologo(odontologo));
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }


    //ELIMINAR

        @DeleteMapping("/{id}")
        public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("se elimino el odontologo");
        }


}
