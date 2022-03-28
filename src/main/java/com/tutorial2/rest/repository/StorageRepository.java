package com.tutorial2.rest.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tutorial2.rest.domain.Storage;

public interface StorageRepository extends CrudRepository<Storage, Long> {

	List<Storage> findAll();
}
