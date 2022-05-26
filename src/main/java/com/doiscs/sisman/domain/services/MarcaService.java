package com.doiscs.sisman.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.doiscs.sisman.domain.data.repositories.MarcaRepository;
import com.doiscs.sisman.domain.model.entity.Marca;

@Service
public class MarcaService implements ServiceInterface<Marca> {

    @Autowired
    private MarcaRepository repository;

    String msg;

    @Override
    public Marca save(Marca marca) {
        marca.setNome(marca.getNome().toUpperCase());

        return repository.save(marca);
    }

    @Override
    public void delete(Marca marca) {

        repository.delete(marca);
    }

    @Override
    public Optional<Marca> findById(Integer id) {

        return repository.findById(id);
    }

    @Override
    public List<Marca> findAll() {

        return repository.findAll(Sort.by(Direction.ASC, "nome"));
    }

    public List<Marca> findByNome(String nome) {

        return repository.findByNome(nome);
    }

    @Override
    public List<Marca> findAtivos(boolean ativo) {

        return repository.findAtivos(ativo);
    }

}
