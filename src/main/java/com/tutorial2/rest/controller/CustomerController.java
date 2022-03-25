package com.tutorial2.rest.controller;

import com.tutorial2.rest.domain.Customers;
import com.tutorial2.rest.domain.Goods;
import com.tutorial2.rest.dto.Customer;
import com.tutorial2.rest.dto.Good;
import com.tutorial2.rest.repository.CustomersRepository;
import com.tutorial2.rest.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8088")
public class CustomerController {

	@Autowired
	private CustomersRepository customersRepository;

	@Autowired
	private GoodsRepository goodsRepository;


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
			return testOpt.isPresent() ? ResponseEntity.status(400).body("Delete Cusomer with id: " + id)
					: ResponseEntity.status(200).body("Error_1 Delete Cusomer with id: " + id);

		}

		return ResponseEntity.status(400).body("Error_0 Delete Cusomer with id: " + id);
	}

	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getCustomers() {

		List<Customers> customers = (List<Customers>) customersRepository.findAll();

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

//----------------------------------------------------------------------------------------------

	@GetMapping("/good/{id}")
	public ResponseEntity<Good> getGood(@PathVariable("id") Long id) {

		Optional<Goods> opt = goodsRepository.findById(id);
		if (opt.isPresent()) {
			Goods goods = opt.get();
			Good result = new Good();
			result.setId(goods.getId());
	//		result.setName(goods.getName());
			result.setNaim(goods.getNaim());
			result.setPrice(goods.getPrice());

			return ResponseEntity.status(200).body(result);

		}

		return ResponseEntity.status(400).body(new Good());
	}

	@DeleteMapping("/good/{id}")
	public ResponseEntity<String> deleteGood(@PathVariable("id") Long id) {

		Optional<Goods> opt = goodsRepository.findById(id);
		if (opt.isPresent()) {

			Goods goods = opt.get();
			goodsRepository.delete(goods);

			Optional<Goods> testOpt = goodsRepository.findById(id);
			return testOpt.isPresent() ? ResponseEntity.status(200).body("Delete Good with id: " + id)
					: ResponseEntity.status(400).body("Error Delete Good with id: " + id);

		}

		return ResponseEntity.status(400).body("Error Delete Good with id: " + id);
	}

	@GetMapping("/goods")
	public ResponseEntity<List<Good>> getGoods() {

		List<Goods> goods = (List<Goods>) goodsRepository.findAll();

		List<Good> result = new ArrayList<>();

		for (int i = 0; i < goods.size(); i++) {

			Good co = new Good();
			co.setId(goods.get(i).getId());
			co.setName(goods.get(i).getName());
			co.setNaim(goods.get(i).getNaim());
			co.setPrice(goods.get(i).getPrice());
			result.add(co);
		}

		return ResponseEntity.status(200).body(result);
	}

	@PostMapping("/good")
	public ResponseEntity<String> saveGood(@RequestBody Good good) {

		Goods goods = new Goods();
		goods.setNaim(good.getNaim());
		goods.setName(good.getName());
		Goods test = goodsRepository.save(goods);
		if (test != null) {
			return ResponseEntity.status(200).body(test.getId().toString());
		}

		return ResponseEntity.status(400).body("Error saving customer!");
	}


}
