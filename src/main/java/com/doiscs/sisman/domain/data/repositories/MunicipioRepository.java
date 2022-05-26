package com.doiscs.sisman.domain.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doiscs.sisman.domain.model.entity.Municipio;

public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {

    @Query("SELECT m FROM Municipio m WHERE nome LIKE CONCAT('%', :nome, '%') ORDER BY m.nome")
    List<Municipio> findByNome(String nome);

    @Query("SELECT m FROM Municipio m ORDER BY m.nome ASC")
    List<Municipio> findMuniciosComEstados();

    @Query("SELECT m FROM Municipio m join fetch m.estado ORDER BY m.nome ASC")
    List<Municipio> findAll();

}
