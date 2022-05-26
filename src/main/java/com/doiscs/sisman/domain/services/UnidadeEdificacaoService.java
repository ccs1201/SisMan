package com.doiscs.sisman.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doiscs.sisman.domain.data.repositories.UnidadeEdificacaoRepository;
import com.doiscs.sisman.domain.model.entity.UnidadeEdificacao;

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
        // TODO Auto-generated method stub
        return repository.findById(id);
    }

    @Override
    public List<UnidadeEdificacao> findAll() {
        // TODO Auto-generated method stub
        return repository.findAll();
    }

    @Override
    public List<UnidadeEdificacao> findByNome(String nome) {
        // TODO Auto-generated method stub
        return repository.findByNome(nome);
    }

    @Override
    public List<UnidadeEdificacao> findAtivos(boolean ativo) {
        System.out.println("não implementado");
        return null;
    }

}
