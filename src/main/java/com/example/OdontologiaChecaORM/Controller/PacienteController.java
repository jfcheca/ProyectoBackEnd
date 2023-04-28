package com.example.OdontologiaChecaORM.Controller;

import com.example.OdontologiaChecaORM.domain.Paciente;
import com.example.OdontologiaChecaORM.exceptions.ResourceNotFoundException;
import com.example.OdontologiaChecaORM.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }


    //REGISTRAR
    @PostMapping
    public Paciente registrarPaciente(@RequestBody Paciente paciente){
        return pacienteService.agregarPaciente(paciente);
    }

    //BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPacienteXid(@PathVariable Long id){
        Optional <Paciente> pacienteBuscado=pacienteService.buscarPaciente(id);
        if (pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        }else {
            return ResponseEntity.notFound().build();
        }

    }

    //BUSCAR TODOS
    @GetMapping
    public ResponseEntity<List<Paciente>> buscarPacientes(){
        return ResponseEntity.ok(pacienteService.buscarTodosLosPacientes());

    }

    //ACTUALIZAR

    @PutMapping
    public Paciente actualizarPaciente(@RequestBody Paciente paciente){
        return pacienteService.actualizarPaciente(paciente);
    }

    //ELIMINAR

    @DeleteMapping("/{id}")
    public  ResponseEntity<String> eliminarPaciente (@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok("se elimino el paciente");
    }

}
