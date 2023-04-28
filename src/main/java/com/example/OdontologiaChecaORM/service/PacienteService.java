package com.example.OdontologiaChecaORM.service;


import com.example.OdontologiaChecaORM.domain.Paciente;
import com.example.OdontologiaChecaORM.exceptions.ResourceNotFoundException;
import com.example.OdontologiaChecaORM.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    private PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente agregarPaciente(Paciente paciente){
        return pacienteRepository.save(paciente);
    }

    public Optional<Paciente> buscarPaciente(Long id){
        return pacienteRepository.findById(id);
    }
    public List<Paciente> buscarTodosLosPacientes(){
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> buscarXEmail(String email){
        return pacienteRepository.findByEmail(email);
    }

    public Paciente actualizarPaciente(Paciente paciente){
        return pacienteRepository.save(paciente);
    }

    public void eliminarPaciente(Long id) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado=pacienteRepository.findById(id);
        if(pacienteBuscado.isPresent()){
            pacienteRepository.deleteById(id);
        }else { throw new ResourceNotFoundException("Error. No existe el paciente con id= " + id);

        }

    }
}
