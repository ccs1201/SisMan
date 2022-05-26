package com.doiscs.sisman.domain.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doiscs.sisman.domain.model.entity.SamplePerson;

public interface SamplePersonRepository extends JpaRepository<SamplePerson, Integer> {

}