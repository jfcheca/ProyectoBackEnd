package com.example.OdontologiaChecaORM.Controller;


import com.example.OdontologiaChecaORM.domain.Odontologo;
import com.example.OdontologiaChecaORM.domain.Paciente;
import com.example.OdontologiaChecaORM.domain.Turno;
import com.example.OdontologiaChecaORM.dto.TurnoDTO;
import com.example.OdontologiaChecaORM.exceptions.BadRequestException;
import com.example.OdontologiaChecaORM.exceptions.ResourceNotFoundException;
import com.example.OdontologiaChecaORM.service.OdontologoService;
import com.example.OdontologiaChecaORM.service.PacienteService;
import com.example.OdontologiaChecaORM.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;
    @Autowired
    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    //REGISTRAR
    @PostMapping
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody TurnoDTO turno) throws BadRequestException, ResourceNotFoundException {
       return ResponseEntity.ok(turnoService.guardarTurno(turno));
    }

    //BUSCAR OPTIONAL
    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarTurnoOptional(@PathVariable Long id){
        Optional<TurnoDTO> turnoBuscado= turnoService.buscarTurno(id);
        //conozco dos salidas 1: el turno 2 es nulo
        if (turnoBuscado.isPresent()){
            //encontrado
            return ResponseEntity.ok(turnoBuscado.get());
        }
        else{
            //no lo encontramos
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id){
        Optional<TurnoDTO> turnoBuscado=turnoService.buscarTurno(id);
        if (turnoBuscado.isPresent()){
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Se eliminó el turno con id= "+id);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se logró eliminar " +
                    "el turno con id= "+id+" dado que el mismo no existe en la base de datos");
        }
    }


    //BUSCAR TODOS
    @GetMapping
    public ResponseEntity<List<TurnoDTO>> listarTurnos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }


    //ACTUALIZAR
}
