package com.doiscs.sisman.domain.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doiscs.sisman.domain.model.entity.Edificacao;

public interface EdificacaoRepository extends JpaRepository<Edificacao, Integer> {

    @Query("SELECT distinct e FROM Edificacao e join fetch e.tipoEdificacao tp WHERE e.nome LIKE CONCAT ('%', :nome,'%') ORDER BY e.nome, e.tipoEdificacao.nome ASC")
    List<Edificacao> findByNome(String nome);

    @Query("SELECT distinct e FROM Edificacao e join fetch e.unidades u WHERE e.ativo = :isAtivo and u.ativo= :isAtivo ORDER BY e.nome, u.nome ASC")
    List<Edificacao> findAtivos(boolean isAtivo);

    @Query("SELECT e FROM Edificacao e join fetch e.tipoEdificacao tp ORDER BY e.nome, e.tipoEdificacao.nome ASC")
    List<Edificacao> findAll();

    @Query("select distinct e from Edificacao e join fetch e.unidades u where e.id =:id order by u.nome asc")
    List<Edificacao> findAmbientes(Integer id);

    @Query("select e from Edificacao e join fetch e.unidades u where e.id = :id order by u.nome")
    Edificacao findUnidades(Integer id);

    @Query("select distinct e from Edificacao e left join fetch e.unidades u left join fetch e.tipoEdificacao tp order by e.nome")
    List<Edificacao> findAllComAmbientes();
}
