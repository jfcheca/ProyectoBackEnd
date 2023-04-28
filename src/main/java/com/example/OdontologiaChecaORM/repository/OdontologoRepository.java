package com.example.OdontologiaChecaORM.repository;

import com.example.OdontologiaChecaORM.domain.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo,Long> {


}
