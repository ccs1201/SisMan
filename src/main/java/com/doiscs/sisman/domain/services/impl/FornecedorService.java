package com.doiscs.sisman.domain.services.impl;

import com.doiscs.sisman.domain.data.repositories.FornecedorRepository;
import com.doiscs.sisman.domain.model.entity.Fornecedor;
import com.doiscs.sisman.domain.services.ServiceInterface;
import com.doiscs.sisman.exceptions.DoisCsCrudException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService implements ServiceInterface<Fornecedor> {

    @Autowired
    FornecedorRepository repository;

    @Override
    public Fornecedor save(Fornecedor entity) throws DoisCsCrudException {

        try {
            entity.setRazaoSocial(entity.getRazaoSocial().toUpperCase());
            entity.setNomeFantasia(entity.getNomeFantasia().toUpperCase());

            return repository.save(entity);
        } catch (DataIntegrityViolationException e) {
            throw new DoisCsCrudException("Fornecedor já cadastrado verifique.", e);
        }
    }

    @Override
    public void delete(Fornecedor entity) throws DoisCsCrudException {
        try {
            repository.delete(entity);
        } catch (DataIntegrityViolationException e) {
            throw new DoisCsCrudException("Fornecedor não pode ser removido, pois existem equipamentos associados a ele.", e);
        }
    }

    @Override
    public Optional<Fornecedor> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Fornecedor> findAll() {
        return repository.findAll(Sort.by(Direction.ASC, "nomeFantasia"));
    }

    @Override
    public List<Fornecedor> findByNome(String nome) {
        return repository.findByNome(nome);
    }

    @Override
    public List<Fornecedor> findAtivos(boolean ativo) {
        return repository.findAtivos(ativo);
    }
}
