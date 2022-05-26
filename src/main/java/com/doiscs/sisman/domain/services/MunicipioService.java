package com.doiscs.sisman.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.doiscs.sisman.domain.data.repositories.MunicipioRepository;
import com.doiscs.sisman.domain.model.entity.Municipio;
import com.doiscs.sisman.exceptions.DoisCsCrudException;

@Service
public class MunicipioService implements ServiceInterface<Municipio> {

    @Autowired
    MunicipioRepository repository;

    @Override
    public Municipio save(Municipio entity) throws DoisCsCrudException {
        try {

            return repository.save(entity);

        } catch (DataIntegrityViolationException e) {

            throw new DoisCsCrudException("Não foi possível salvar o município " + e.getLocalizedMessage(), e);
        }
    }

    @Override
    public void delete(Municipio entity) throws DoisCsCrudException {

        try {
            repository.delete(entity);

        } catch (DataIntegrityViolationException e) {

            throw new DoisCsCrudException(
                    "Não foi possível remover o município " + entity.getNome() + " motivo: " + e.getLocalizedMessage(),
                    e);
        }

    }

    @Override
    public Optional<Municipio> findById(Integer id) {
        // TODO Auto-generated method stub
        return repository.findById(id);
    }

    @Override
    public List<Municipio> findAll() {
        // TODO Auto-generated method stub
        return repository.findAll();
    }

    @Override
    public List<Municipio> findByNome(String nome) {
        // TODO Auto-generated method stub
        return repository.findByNome(nome);
    }

    @Override
    public List<Municipio> findAtivos(boolean ativo) {
        // TODO Auto-generated method stub
        return null;
    }

}
