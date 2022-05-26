package com.doiscs.sisman.domain.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doiscs.sisman.domain.model.entity.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {

    // @Query("SELECT f FROM Fornecedor f WHERE nomeFantasia LIKE CONCAT ('%',
    // :nome, '%') or razaoSocial LIKE CONCAT ('%', :nome, '%') ORDER BY
    // nomeFantasia ASC")
    @Query("select f from Fornecedor f WHERE nomeFantasia LIKE CONCAT ('%', :nome, '%') or f.razaoSocial LIKE CONCAT ('%', :nome, '%') ORDER BY f.nomeFantasia ASC")
    List<Fornecedor> findByNome(String nome);

    @Query("SELECT f FROM Fornecedor f WHERE f.ativo = :ativo ORDER BY f.nomeFantasia ASC")
    List<Fornecedor> findAtivos(boolean ativo);

}
