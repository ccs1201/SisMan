package com.doiscs.sisman.domain.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doiscs.sisman.domain.model.entity.Ambiente;

public interface AmbienteRepository extends JpaRepository<Ambiente, Integer> {

    @Query("select a from Ambiente a where a.nome LIKE CONCAT('%', :nome, '%') or unidadeEdificacao.nome LIKE CONCAT('%', :nome, '%') or"
            + " unidadeEdificacao.edificacao.nome LIKE CONCAT('%', :nome, '%') order by nome asc")
    List<Ambiente> findByNome(String nome);

    @Query("select a from Ambiente a order by a.nome asc")
    List<Ambiente> findAll();

    @Query("select a from Ambiente a where a.ativo = :ativo order by a.nome asc")
    List<Ambiente> findAtivos(boolean ativo);

    @Query("select a From Ambiente a join fetch a.unidadeEdificacao u join fetch u.edificacao e "
            + "join fetch e.tipoEdificacao order by a.nome, u.nome, e.nome")
    List<Ambiente> findAllEager();

    @Query("select a From Ambiente a join fetch a.unidadeEdificacao u join fetch u.edificacao e "
            + "join fetch e.tipoEdificacao where a.nome LIKE CONCAT('%', :nome, '%') or u.nome "
            + "LIKE CONCAT('%', :nome, '%') or e.nome LIKE CONCAT('%', :nome, '%') "
            + "order by a.nome, u.nome, e.nome")
    List<Ambiente> findByNomeEager(String nome);

    List<Ambiente> findByUnidadeEdificacaoId(int edficacaoId);
}
