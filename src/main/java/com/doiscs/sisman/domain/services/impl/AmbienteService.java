package com.doiscs.sisman.domain.services.impl;

import com.doiscs.sisman.domain.data.repositories.AmbienteRepository;
import com.doiscs.sisman.domain.model.entity.Ambiente;
import com.doiscs.sisman.domain.services.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AmbienteService implements ServiceInterface<Ambiente> {
    @Autowired
    private AmbienteRepository repository;

    @Override
    public Ambiente save(Ambiente entity) {
        entity.setNome(entity.getNome().toUpperCase());
        return repository.save(entity);
    }

    @Override
    public void delete(Ambiente entity) {
        repository.delete(entity);
    }

    @Override
    public Optional<Ambiente> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Ambiente> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Ambiente> findByNome(String nome) {
        return repository.findByNome(nome);
    }

    @Override
    public List<Ambiente> findAtivos(boolean ativo) {
        return repository.findAtivos(ativo);
    }

    public List<Ambiente> findAllEager() {
        return repository.findAllEager();
    }

    public List<Ambiente> findByNomeEager(String nome) {
        return repository.findByNomeEager(nome);
    }

    public List<Ambiente> findByUnidadeEdificacaoId(int edficacaoId) {
        return repository.findByUnidadeEdificacaoId(edficacaoId);
    }
}
