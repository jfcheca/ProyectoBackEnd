package com.example.OdontologiaChecaORM.service;


import com.example.OdontologiaChecaORM.domain.Odontologo;
import com.example.OdontologiaChecaORM.repository.OdontologoRepository;
import com.example.OdontologiaChecaORM.exceptions.ResourceNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    Logger logger = Logger.getLogger(getClass().getName());

    private OdontologoRepository odontologoRepository;
    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }
    public Optional<Odontologo> buscarOdontologo(Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado= odontologoRepository.findById(id);
        if(odontologoBuscado.isPresent()){
            logger.info("Se encontro el odontologo con el id: " + id);
            return odontologoBuscado;
        }
        logger.warn("Error. No se pudo encontrar el odontologo con el id: " + id);
        throw new ResourceNotFoundException("No se encontro el odontologo con id: " + id);

    }
    public List<Odontologo> buscarOdontologos(){
        List<Odontologo> odontologosList = odontologoRepository.findAll();
        logger.info("La cantidad de odontologos en la base de datos es de: "+ " "+ odontologosList.size() );

        return odontologoRepository.findAll();
    }
    public Odontologo crearOdontologo(Odontologo odontologo){
        logger.info("Se acaba de crear el Odontologo llamado: " + odontologo.getNombre()+", " + odontologo.getApellido() + " "+" con el siguiente numero de matricula: " +" " + odontologo.getMatricula());
        return odontologoRepository.save(odontologo);
    }
    public Optional<Odontologo> eliminarOdontologo(Long id) throws  ResourceNotFoundException{
        Optional<Odontologo> odontologoABuscar= odontologoRepository.findById(id);
        if(odontologoABuscar.isPresent()){
            logger.info("Se elimino el odontologo con el id: " + id);
            odontologoRepository.deleteById(id);
        }
        else {
            logger.info("No existe el odontologo con el id: " + id );
            throw new ResourceNotFoundException("No se encontro el odontologo+ " + id);
        }

return null;
    }

    public Odontologo actualizarOdontologo(Odontologo odontologo){
        logger.info("Se acaba de actualizar el Odontologo con el id: " + odontologo.getId() + " " +"llamado " + odontologo.getNombre());
        return odontologoRepository.save(odontologo);
    }
}
