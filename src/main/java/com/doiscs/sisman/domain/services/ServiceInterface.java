package com.doiscs.sisman.domain.services;

import java.util.List;
import java.util.Optional;

import com.doiscs.sisman.exceptions.DoisCsCrudException;

public interface ServiceInterface<T> {

    public T save(final T entity) throws DoisCsCrudException;

    public void delete(final T entity) throws DoisCsCrudException;

    public Optional<T> findById(final Integer id);

    public List<T> findAll();

    public List<T> findByNome(String nome);

    public List<T> findAtivos(boolean ativo);

}
