package com.doiscs.sisman.domain.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doiscs.sisman.domain.model.entity.Equipamento;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Integer> {

    @Query("SELECT e FROM Equipamento e WHERE e.nome LIKE COMCAT ('%', :nome, '%') ORDER BY nome")
    List<Equipamento> findByNome(String nome);

    @Query("SELECT e FROM Equipamento e ORDER BY nome")
    List<Equipamento> findByAll();

}
