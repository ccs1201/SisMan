package com.doiscs.sisman.domain.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doiscs.sisman.domain.model.entity.TipoEquipamento;

public interface TipoEquipamentoRepository extends JpaRepository<TipoEquipamento, Integer> {

    @Query("select t from TipoEquipamento t where t.nome like CONCAT ('%', :descricao, '%') ORDER BY nome ASC")
    List<TipoEquipamento> findByDescricao(String descricao);

    @Query("select t from TipoEquipamento t ORDER BY nome ASC")
    List<TipoEquipamento> findAll();

    @Query("SELECT t FROM TipoEquipamento t WHERE t.ativo = :ativo ORDER BY nome ASC")
    List<TipoEquipamento> findAtivos(boolean ativo);

}
