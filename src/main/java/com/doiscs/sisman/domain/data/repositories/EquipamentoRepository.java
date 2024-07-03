package com.doiscs.sisman.domain.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doiscs.sisman.domain.model.entity.Equipamento;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Integer> {

    List<Equipamento> findByNome(String nome);

    @Query("SELECT e FROM Equipamento e ORDER BY e.nome")
    List<Equipamento> findByAll();

}
