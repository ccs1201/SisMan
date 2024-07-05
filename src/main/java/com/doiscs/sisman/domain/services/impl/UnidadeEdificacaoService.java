package com.doiscs.sisman.domain.services.impl;

import com.doiscs.sisman.domain.data.repositories.UnidadeEdificacaoRepository;
import com.doiscs.sisman.domain.model.entity.UnidadeEdificacao;
import com.doiscs.sisman.domain.services.ServiceInterface;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadeEdificacaoService implements ServiceInterface<UnidadeEdificacao> {

    @Autowired
    private UnidadeEdificacaoRepository repository;

    @Override
    public UnidadeEdificacao save(UnidadeEdificacao entity) {
        entity.setNome(entity.getNome().toUpperCase());
        return repository.save(entity);
    }

    @Override
    public void delete(UnidadeEdificacao entity) {
        repository.delete(entity);
    }

    @Override
    public Optional<UnidadeEdificacao> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<UnidadeEdificacao> findAll() {
        return repository.findAll();
    }

    @Override
    public List<UnidadeEdificacao> findByNome(String nome) {
        return repository.findByNome(nome);
    }

    @Override
    public List<UnidadeEdificacao> findAtivos(boolean ativo) {
        throw new NotImplementedException();
    }
}
