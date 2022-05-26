package com.doiscs.sisman.domain.data.repositories;

import java.util.List;

import javax.persistence.OrderBy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doiscs.sisman.domain.model.entity.TipoEdificacao;

public interface TipoEdificacaoRepository extends JpaRepository<TipoEdificacao, Integer> {

    @Query("select te from TipoEdificacao te where te.nome like CONCAT ('%', :nome, '%') ORDER BY nome ASC ")
    List<TipoEdificacao> findByNome(String nome);

    @Query("select te from TipoEdificacao te order by nome asc")
    List<TipoEdificacao> findAll();

    @OrderBy("nome ASC")
    List<TipoEdificacao> ativo(boolean ativo);

}
