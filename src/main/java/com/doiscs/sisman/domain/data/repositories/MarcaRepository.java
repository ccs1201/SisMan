package com.doiscs.sisman.domain.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.doiscs.sisman.domain.model.entity.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Integer> {

    @Query("select m from Marca m where m.nome like CONCAT ('%', :nome, '%') ORDER BY nome")
    List<Marca> findByNome(@Param("nome") String nome);

    //@Query("select m from Marca m ORDER BY nome")
    List<Marca> findAll();

    @Query("select m from Marca m WHERE m.ativo = :ativo ORDER BY nome")
    List<Marca> findAtivos(boolean ativo);
}
