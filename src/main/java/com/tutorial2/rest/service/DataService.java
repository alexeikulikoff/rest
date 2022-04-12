package com.tutorial2.rest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tutorial2.rest.domain.Customers;
import com.tutorial2.rest.dto.Customer;
import com.tutorial2.rest.repository.CustomersRepository;

@Service
public class DataService {

	@Autowired
	private CustomersRepository customersRepository;

	public ResponseEntity<Customer> getCustomer(Long id) {
		Optional<Customers> opt = customersRepository.findById(id);
		if (opt.isPresent()) {
			Customers customers = opt.get();
			Customer result = new Customer();
			result.setId(customers.getId());
			result.setName(customers.getName());
			return ResponseEntity.status(200).body(result);

		}

		return ResponseEntity.status(400).body(new Customer());
	}

}
