package com.example.OdontologiaChecaORM.repository;

import com.example.OdontologiaChecaORM.domain.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno,Long> {

}
