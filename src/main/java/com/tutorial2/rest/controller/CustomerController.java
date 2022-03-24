package com.tutorial2.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial2.rest.domain.Customers;
import com.tutorial2.rest.dto.Customer;
import com.tutorial2.rest.repository.CustomersRepository;

@RestController
@CrossOrigin(origins = "http://localhost:8088")
public class CustomerController {

	@Autowired
	private CustomersRepository customersRepository;

	@GetMapping("/test")
	public ResponseEntity<String> test() {

		return ResponseEntity.ok("HelloWorld");
	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {

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

	@DeleteMapping("/customer/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id) {

		Optional<Customers> opt = customersRepository.findById(id);
		if (opt.isPresent()) {

			Customers customers = opt.get();
			customersRepository.delete(customers);

			Optional<Customers> testOpt = customersRepository.findById(id);
			return testOpt.isPresent() ? ResponseEntity.status(200).body("Delete Cusomer with id: " + id)
					: ResponseEntity.status(400).body("Error Delete_0 Cusomer with id: " + id);

		}

		return ResponseEntity.status(400).body("Error Delete_1 Cusomer with id: " + id);
	}

	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getCustomers() {

		List<Customers> customers = customersRepository.findAll();

		List<Customer> result = new ArrayList<>();

		for (int i = 0; i < customers.size(); i++) {

			Customer co = new Customer();
			co.setId(customers.get(i).getId());
			co.setName(customers.get(i).getName());
			result.add(co);
		}

		return ResponseEntity.status(200).body(result);
	}

	@PostMapping("/customer")
	public ResponseEntity<String> saveCustomer(@RequestBody Customer customer) {

		Customers customers = new Customers();
		customers.setName(customer.getName());

		Customers test = customersRepository.save(customers);
		if (test != null) {
			return ResponseEntity.status(200).body(test.getId().toString());
		}

		return ResponseEntity.status(400).body("Error saving customer!");
	}

}
