package com.doiscs.sisman.domain.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doiscs.sisman.domain.model.entity.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {

    @Query("select e from Estado e where nome like concat ('%', :nome, '%') order by nome asc")
    List<Estado> findByNome(String nome);


    @Query("select distinct e from Estado e join fetch e.municipios m order by e.nome, m.nome ASC")
    List<Estado> findAllEager();

    @Query("select e from Estado e join fetch e.municipios m where m.estado.id = :id order by m.nome asc")
    Estado findMunicipios(int id);

}
