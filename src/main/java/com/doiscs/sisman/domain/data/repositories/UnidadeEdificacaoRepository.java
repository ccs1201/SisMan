package com.doiscs.sisman.domain.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doiscs.sisman.domain.model.entity.UnidadeEdificacao;

public interface UnidadeEdificacaoRepository extends JpaRepository<UnidadeEdificacao, Integer> {

    @Query("SELECT u FROM UnidadeEdificacao u join fetch u.edificacao WHERE u.nome LIKE CONCAT ('%', :nome, '%') ORDER BY u.nome")
    List<UnidadeEdificacao> findByNome(String nome);

    @Query("SELECT u FROM UnidadeEdificacao u join fetch u.edificacao ORDER BY u.nome")
    List<UnidadeEdificacao> findAll();

    @Query("SELECT u FROM UnidadeEdificacao u join fetch u.ambientes ORDER BY u.nome")
    List<UnidadeEdificacao> findAllEager();

}
