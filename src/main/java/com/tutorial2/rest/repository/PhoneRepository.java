package com.tutorial2.rest.repository;

import com.tutorial2.rest.domain.Phones;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends CrudRepository<Phones, Long> {

}
