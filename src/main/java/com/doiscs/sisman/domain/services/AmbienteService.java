package com.doiscs.sisman.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.doiscs.sisman.domain.data.repositories.AmbienteRepository;
import com.doiscs.sisman.domain.model.entity.Ambiente;

@Service
public class AmbienteService implements ServiceInterface<Ambiente> {
    @Autowired
    private AmbienteRepository repo;

    @Override
    public Ambiente save(Ambiente entity) {

        entity.setNome(entity.getNome().toUpperCase());

        return repo.save(entity);
    }

    @Override
    public void delete(Ambiente entity) {
        repo.delete(entity);

    }

    @Override
    public Optional<Ambiente> findById(Integer id) {

        return repo.findById(id);
    }

    @Override
    public List<Ambiente> findAll() {

        return repo.findAll();
    }

    @Override
    public List<Ambiente> findByNome(String nome) {

        return repo.findByNome(nome);
    }

    @Override
    public List<Ambiente> findAtivos(boolean ativo) {

        return repo.findAtivos(ativo);
    }

    public List<Ambiente> findAllEager() {
        return repo.findAllEager();
    }

    public List<Ambiente> findByNomeEager(String nome) {

        return repo.findByNomeEager(nome);

    }

    public List<Ambiente> findByUnidadeEdificacaoId(int edficacaoId){
        return repo.findByUnidadeEdificacaoId(edficacaoId);

    }

}
