package com.doiscs.sisman.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.doiscs.sisman.domain.data.repositories.EstadoRepository;
import com.doiscs.sisman.domain.model.entity.Estado;
import com.doiscs.sisman.exceptions.DoisCsCrudException;

@Service
public class EstadoService implements ServiceInterface<Estado> {

    @Autowired
    private EstadoRepository repository;

    @Override
    public Estado save(Estado entity) throws DoisCsCrudException {
        try {

            return repository.save(entity);
        } catch (DataIntegrityViolationException e) {
            throw new DoisCsCrudException("Erro ao salvar Estado", e);
        }
    }

    @Override
    public void delete(Estado entity) throws DoisCsCrudException {
        try {

            repository.delete(entity);

        } catch (DataIntegrityViolationException e) {
            throw new DoisCsCrudException("Erro ao remover Estado", e);
        }

    }

    @Override
    public Optional<Estado> findById(Integer id) {
        // TODO Auto-generated method stub
        return repository.findById(id);
    }

    @Override
    public List<Estado> findAll() {
        // TODO Auto-generated method stub
        return repository.findAll();
    }

    @Override
    public List<Estado> findByNome(String nome) {
        // TODO Auto-generated method stub
        return repository.findByNome(nome);
    }

    @Override
    public List<Estado> findAtivos(boolean ativo) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Estado> findAllEager() {

        return repository.findAllEager();

    }

    public Estado findMunicipios(Estado estado) {

        return repository.findMunicipios(estado.getId());
    }

}
