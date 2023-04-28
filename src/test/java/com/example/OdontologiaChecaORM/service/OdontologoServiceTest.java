package com.example.OdontologiaChecaORM.service;

import com.example.OdontologiaChecaORM.domain.Odontologo;
import com.example.OdontologiaChecaORM.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    // GUARDAR ODONTOLOGO
    @Test
    @Order(1)
    public void guardarOdontologoTest() {
        Odontologo odontologoAGuardar = new Odontologo(1L, "Ramos", "Juan", "987879");
        Odontologo odontologoGuardado = odontologoService.crearOdontologo(odontologoAGuardar);
        assertEquals(1L, odontologoGuardado.getId());
        System.out.println(odontologoGuardado.getApellido());

    }

    //BUSCAR POR ID
    @Test
    @Order(2)
    public void buscarOdontologoPorIdTest() throws ResourceNotFoundException {
        Long idABuscar = 1L;
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(idABuscar);
        assertNotNull(odontologoBuscado.get().getNombre());
        System.out.println(odontologoBuscado.get().getNombre());
    }

    //BUSCAR TODOS LOS ODONTOLOGOS
    @Test
    @Order(3)
    public void buscarTodosLosOdontologos() {
        List<Odontologo> odontologosABuscar = odontologoService.buscarOdontologos();
        assertEquals(1, odontologosABuscar.size());
        System.out.println(odontologosABuscar.size());

    }

    //ACTUALIZAR ODONTOLOGO
    @Test
    @Order(4)
    public void actualizarOdontologo() {

        Odontologo odontologoAActualizar = new Odontologo(1L, "Ramos", "Juan", "987879");
        Odontologo odontologoActualizado = odontologoService.actualizarOdontologo(odontologoAActualizar);
        odontologoActualizado.setNombre("JULIO");
        odontologoActualizado.setApellido("LUNA");
        assertEquals("LUNA", odontologoActualizado.getApellido());
        System.out.println(odontologoActualizado.getMatricula());
    }

    ////ELIMINAR
    @Test
    @Order(5)
    public void eliminarOdontologo() throws ResourceNotFoundException {
        Long idAEliminar = 1L;
     //   Odontologo odontologoAEliminar = new Odontologo(1L, "Gonzales", "Lucas", "987879");
        Optional<Odontologo> odontologoEliminado = odontologoService.eliminarOdontologo(idAEliminar);
        assertNull(odontologoEliminado);


    }
}
    //  Long idOdontologo = 1L;
    // Odontologo odontologoGuardado = odontologoService.crearOdontologo(odontologoAEliminar);


//  @Test
//  @Order(5)
//  public void eliminarPacientePorId() {
//        Long idABuscar = 1L;
//        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(idABuscar);
//        assertNotNull(odontologoBuscado.get().getNombre());
//        System.out.println(odontologoBuscado.get().getNombre());
//    }
//
//}
