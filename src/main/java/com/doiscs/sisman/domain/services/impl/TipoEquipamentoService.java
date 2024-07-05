package com.doiscs.sisman.domain.services.impl;

import com.doiscs.sisman.domain.data.repositories.TipoEquipamentoRepository;
import com.doiscs.sisman.domain.model.entity.TipoEquipamento;
import com.doiscs.sisman.domain.services.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoEquipamentoService implements ServiceInterface<TipoEquipamento> {

    @Autowired
    private TipoEquipamentoRepository repository;

    @Override
    public TipoEquipamento save(TipoEquipamento entity) {
        entity.setNome(entity.getNome().toUpperCase());
        return repository.save(entity);
    }

    @Override
    public void delete(TipoEquipamento entity) {
        repository.delete(entity);
    }

    @Override
    public Optional<TipoEquipamento> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<TipoEquipamento> findAll() {
        return repository.findAll();
    }

    public List<TipoEquipamento> findByDescricao(String value) {
        return repository.findByDescricao(value);
    }

    @Override
    public List<TipoEquipamento> findByNome(String nome) {
        return repository.findByDescricao(nome);
    }

    @Override
    public List<TipoEquipamento> findAtivos(boolean ativo) {
        return repository.findAtivos(ativo);
    }
}
