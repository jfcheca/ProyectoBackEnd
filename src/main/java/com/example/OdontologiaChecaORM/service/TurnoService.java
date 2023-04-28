package com.example.OdontologiaChecaORM.service;

import com.example.OdontologiaChecaORM.domain.Odontologo;
import com.example.OdontologiaChecaORM.domain.Paciente;
import com.example.OdontologiaChecaORM.domain.Turno;
import com.example.OdontologiaChecaORM.dto.TurnoDTO;
import com.example.OdontologiaChecaORM.repository.TurnoRepository;
import com.example.OdontologiaChecaORM.exceptions.BadRequestException;
import com.example.OdontologiaChecaORM.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class TurnoService {

    //INYECCIONES
    private TurnoRepository turnoRepository;
    private OdontologoService odontologoService;
    private PacienteService pacienteService;


    @Autowired
    public TurnoService(TurnoRepository turnoRepository, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoRepository = turnoRepository;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    //GUARDAR
    public TurnoDTO guardarTurno(TurnoDTO turno) throws BadRequestException, ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado=pacienteService.buscarPaciente(turno.getPaciente_id());
        Optional<Odontologo> odontologoBuscado=odontologoService.buscarOdontologo(turno.getOdontologo_id());
        if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()){
            return convertirTurnoaTurnoDTO((turnoRepository.save(convertirTurnoDTOaTurno(turno))));
        }
        else{
            throw new BadRequestException("Error. No se pudo crear el turno");
        }

    }

    //BUSCAR
    public Optional<TurnoDTO> buscarTurno(Long id) {
        Optional<Turno> turnoBuscado=turnoRepository.findById(id);
        if (turnoBuscado.isPresent()){
            return Optional.of(convertirTurnoaTurnoDTO(turnoBuscado.get()));
        }else {
            return Optional.empty();
        }
    }

    //BUSCAR TODOS
    public List <TurnoDTO> buscarTodos(){
        List<Turno> listaTurnos = turnoRepository.findAll();
        List<TurnoDTO> listaTurnoDTO = new ArrayList<>();
        for (Turno listaTurno: listaTurnos) {
            listaTurnoDTO.add(convertirTurnoaTurnoDTO(listaTurno));
        }
        return listaTurnoDTO;
    }

    //ELIMINAR
    public void eliminarTurno(Long id){
        Optional<Turno> turnoBuscado=turnoRepository.findById(id);
                if(turnoBuscado.isPresent())
        turnoRepository.deleteById(id);
    }

//
//    public void eliminarPaciente(Long id) throws ResourceNotFoundException{
//        Optional<Paciente> pacienteBuscado=pacienteRepository.findById(id);
//        if(pacienteBuscado.isPresent()){
//            pacienteRepository.deleteById(id);
//        }else { throw new ResourceNotFoundException("Error. No existe el paciente con id= " + id);
//
//        }

    //ACTUALIZAR TURNO



    //CONVERTIR TURNO DTO A TURNO
    private Turno convertirTurnoDTOaTurno(TurnoDTO turnoDTO){
        Turno turno= new Turno();
        Paciente paciente= new Paciente();
        Odontologo odontologo= new Odontologo();
        turno.setId(turnoDTO.getId());
        turno.setFecha(turnoDTO.getFecha());
        paciente.setId(turnoDTO.getPaciente_id());
        paciente.setNombre(turnoDTO.getNombre_paciente());
        odontologo.setId(turnoDTO.getOdontologo_id());
        odontologo.setNombre(turnoDTO.getNombre_odontologo());
        //vincular los objetos
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);
        //el turno esta listo
        return turno;
    }

    //CONVERTIR TURNO A TURNODTO
    private TurnoDTO convertirTurnoaTurnoDTO(Turno turno){
        TurnoDTO turnoDTO= new TurnoDTO();
        turnoDTO.setId(turno.getId());
        turnoDTO.setOdontologo_id(turno.getOdontologo().getId());
        turnoDTO.setPaciente_id(turno.getPaciente().getId());
        turnoDTO.setFecha(turno.getFecha());
        turnoDTO.setNombre_odontologo(turno.getOdontologo().getNombre());
        turnoDTO.setNombre_paciente(turno.getPaciente().getNombre());
        return turnoDTO;
    }
}


