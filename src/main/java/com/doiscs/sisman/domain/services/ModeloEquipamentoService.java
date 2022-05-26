package com.doiscs.sisman.domain.services;

import com.doiscs.sisman.domain.data.repositories.ModeloEquipamentoRepository;
import com.doiscs.sisman.domain.model.entity.ModeloEquipamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModeloEquipamentoService implements ServiceInterface<ModeloEquipamento> {

    @Autowired
    ModeloEquipamentoRepository repository;

    @Override
    public ModeloEquipamento save(ModeloEquipamento entity) {

        entity.setNome(entity.getNome().toUpperCase());

        return repository.save(entity);
    }

    @Override
    public void delete(ModeloEquipamento entity) {
        repository.delete(entity);

    }

    @Override
    public Optional<ModeloEquipamento> findById(Integer id) {

        return repository.findById(id);
    }

    @Override
    public List<ModeloEquipamento> findAll() {

        return repository.findAll();
    }

    @Override
    public List<ModeloEquipamento> findByNome(String nome) {

        return repository.findByNome(nome);
    }

    public List<ModeloEquipamento> findAtivos(boolean ativo) {

        return repository.findAtivos(ativo);

    }

}
