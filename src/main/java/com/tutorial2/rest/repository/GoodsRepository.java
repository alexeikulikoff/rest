package com.tutorial2.rest.repository;

import com.tutorial2.rest.domain.Goods;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GoodsRepository extends CrudRepository<Goods, Long> {

	Iterable<Goods> findAll();

	Optional<Goods> findById(Long id);

}
