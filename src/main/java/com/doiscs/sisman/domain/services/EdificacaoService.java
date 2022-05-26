package com.doiscs.sisman.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doiscs.sisman.domain.data.repositories.EdificacaoRepository;
import com.doiscs.sisman.domain.model.entity.Edificacao;
import com.doiscs.sisman.domain.model.entity.UnidadeEdificacao;

@Service
public class EdificacaoService implements ServiceInterface<Edificacao> {

    @Autowired
    private EdificacaoRepository repository;

    @Override
    public Edificacao save(Edificacao entity) {

        entity.setNome(entity.getNome().toUpperCase());

        return repository.save(entity);
    }

    @Override
    public void delete(Edificacao entity) {
        repository.delete(entity);

    }

    @Override
    public Optional<Edificacao> findById(Integer id) {

        return repository.findById(id);
    }

    @Override
    public List<Edificacao> findAll() {

        return repository.findAll();
    }

    @Override
    public List<Edificacao> findByNome(String nome) {

        return repository.findByNome(nome);
    }

    public List<Edificacao> findAtivos(boolean ativo) {

        return repository.findAtivos(ativo);
    }

    public List<Edificacao> getAmbientes(Edificacao edificacao) {

        return repository.findAmbientes(edificacao.getId());

    }

    public List<UnidadeEdificacao> getUnidades(Edificacao edificacao) {

        List<UnidadeEdificacao> unidades = repository.findUnidades(edificacao.getId()).getUnidades();

        return unidades;
    }

    public List<Edificacao> findAllComAmbientes() {

        return repository.findAllComAmbientes();

    }

}
