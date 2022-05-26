package com.doiscs.sisman.domain.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doiscs.sisman.domain.model.entity.ModeloEquipamento;

public interface ModeloEquipamentoRepository extends JpaRepository<ModeloEquipamento, Integer> {

    @Query("SELECT m FROM ModeloEquipamento m WHERE m.nome LIKE CONCAT ('%', :nome, '%') "
            + "OR m.marca.nome LIKE CONCAT ('%', :nome, '%') ORDER BY nome, m.marca.nome ASC")
    List<ModeloEquipamento> findByNome(String nome);

    @Query("SELECT m FROM ModeloEquipamento m join fetch m.tipoEquipamento te join fetch m.marca ma ORDER BY m.nome ASC")
    List<ModeloEquipamento> findAll();

    @Query("SELECT m FROM ModeloEquipamento m WHERE ativo = :ativo ORDER BY nome ASC")
    List<ModeloEquipamento> findAtivos(boolean ativo);

}
