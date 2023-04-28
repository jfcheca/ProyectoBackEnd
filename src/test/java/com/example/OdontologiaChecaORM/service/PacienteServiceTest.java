package com.example.OdontologiaChecaORM.service;

import com.example.OdontologiaChecaORM.domain.Domicilio;
import com.example.OdontologiaChecaORM.domain.Paciente;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    //GUARDAR UN PACIENTE
    @Test
    @Order(1)
    public void guardarPacienteTest() {
        Domicilio domicilioAGuardar = new Domicilio("Calle A", "846", "Capital", "Cordoba");
        Paciente pacienteAGuardar = new Paciente("Checasa", "Javito", "8513111", LocalDate.of(2023, 05, 25), domicilioAGuardar, "checa1@gmail.com");
        Paciente pacienteGuardado = pacienteService.agregarPaciente(pacienteAGuardar);
        assertEquals(2L, pacienteGuardado.getId());
    }

    //BUSCAR PACIENTE
    @Test
    @Order(2)
    public void buscarPacientePorIdTest() {
        Long idABuscar = 1L;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(idABuscar);
        assertNotNull(pacienteBuscado.get().getNombre());
        System.out.println(pacienteBuscado.get().getNombre());
    }

    //BUSCAR TODOS LOS PACIENTES
    @Test
    @Order(3)
    public void buscarTodosLosPacientes() {
        List<Paciente> pacientesABuscar = pacienteService.buscarTodosLosPacientes();
        assertNotNull(pacientesABuscar);

    }
//ACTUALIZAR PACIENTE

    @Test
    @Order(4)
    public void actualizarPaciente() {

//        Long idPacienteCreado = 1L;

        Domicilio domicilioAGuardar = new Domicilio("Calle A", "846", "Capital", "Cordoba");
        Paciente pacienteAActualizar = new Paciente("Checasa", "Javito", "8513111", LocalDate.of(2023, 05, 25), domicilioAGuardar, "checa1@gmail.com");
        Paciente pacienteActualizado = pacienteService.actualizarPaciente(pacienteAActualizar);
        pacienteActualizado.setNombre("JUANITA");
        pacienteActualizado.setApellido("VIALE");
        assertEquals("VIALE", pacienteActualizado.getApellido());
    }
//ELIMINAR PACIENTE

@Test
@Order(5)
    public void eliminarPacientePorId() {
        Long idABuscar = 1L;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(idABuscar);
        assertNotNull(pacienteBuscado.get().getNombre());
        System.out.println(pacienteBuscado.get().getNombre());
    }
}