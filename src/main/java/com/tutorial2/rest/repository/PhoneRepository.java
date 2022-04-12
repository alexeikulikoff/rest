package com.tutorial2.rest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tutorial2.rest.domain.Phones;

@Repository
public interface PhoneRepository extends CrudRepository<Phones, Long> {

}
