package com.doiscs.sisman.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doiscs.sisman.domain.data.repositories.TipoEquipamentoRepository;
import com.doiscs.sisman.domain.model.entity.TipoEquipamento;

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
        // TODO Auto-generated method stub
        return repository.findById(id);
    }

    @Override
    public List<TipoEquipamento> findAll() {
        // TODO Auto-generated method stub
        return repository.findAll();
    }

    public List<TipoEquipamento> findByDescricao(String value) {
        return repository.findByDescricao(value);

    }

    @Override
    public List<TipoEquipamento> findByNome(String nome) {
        // TODO Auto-generated method stub
        return repository.findByDescricao(nome);
    }

    @Override
    public List<TipoEquipamento> findAtivos(boolean ativo) {

        return repository.findAtivos(ativo);
    }

}
