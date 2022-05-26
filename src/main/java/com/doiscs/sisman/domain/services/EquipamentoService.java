package com.doiscs.sisman.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doiscs.sisman.domain.data.repositories.EquipamentoRepository;
import com.doiscs.sisman.domain.model.entity.Equipamento;

@Service
public class EquipamentoService implements ServiceInterface<Equipamento> {

    @Autowired
    private EquipamentoRepository repository;

    @Override
    public Equipamento save(Equipamento entity) {

        entity.setNome(entity.getNome().toUpperCase());

        return repository.save(entity);
    }

    @Override
    public void delete(Equipamento entity) {
        repository.delete(entity);

    }

    @Override
    public Optional<Equipamento> findById(Integer id) {
        // TODO Auto-generated method stub
        return repository.findById(id);
    }

    @Override
    public List<Equipamento> findAll() {
        // TODO Auto-generated method stub
        return repository.findAll();
    }

    @Override
    public List<Equipamento> findByNome(String nome) {

        return repository.findByNome(nome);
    }

    @Override
    public List<Equipamento> findAtivos(boolean ativo) {
        System.out.println("não implementado");
        return null;
    }

}
