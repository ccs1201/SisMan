package com.doiscs.sisman.domain.services.impl;

import com.doiscs.sisman.domain.data.repositories.TipoEdificacaoRepository;
import com.doiscs.sisman.domain.model.entity.TipoEdificacao;
import com.doiscs.sisman.domain.services.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoEdificacaoService implements ServiceInterface<TipoEdificacao> {

    @Autowired
    private TipoEdificacaoRepository repository;

    @Override
    public TipoEdificacao save(TipoEdificacao entity) {
        entity.setNome(entity.getNome().toUpperCase());
        return repository.save(entity);
    }

    @Override
    public void delete(TipoEdificacao entity) {
        repository.delete(entity);
    }

    @Override
    public Optional<TipoEdificacao> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<TipoEdificacao> findAll() {
        return repository.findAll();
    }

    public List<TipoEdificacao> findByNome(String nome) {
        return repository.findByNome(nome);
    }

    public List<TipoEdificacao> findAtivos(boolean ativo) {
        return repository.ativo(ativo);
    }
}
