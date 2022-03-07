package com.tutorial2.rest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.tutorial2.rest.domain.Customers;

public interface CustomersRepository extends CrudRepository<Customers, Long> {

	List<Customers> findAll();

	Optional<Customers> findById(Long id);

}
