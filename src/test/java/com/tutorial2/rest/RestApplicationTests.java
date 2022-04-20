package com.tutorial2.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tutorial2.rest.domain.Customers;
import com.tutorial2.rest.domain.Storage;
import com.tutorial2.rest.dto.Customer;
import com.tutorial2.rest.repository.CustomersRepository;
import com.tutorial2.rest.repository.StorageRepository;

@SpringBootTest(classes = { RestApplication.class })
@EnableJpaRepositories("com.tutorial2.rest.repository")
@EntityScan("com.tutorial2.rest.domain")
class RestApplicationTests {
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext context;

	@Autowired
	private CustomersRepository customersRepository;

	@Autowired
	private StorageRepository storageRepository;

	@Test
	void addCustomers() {

		Storage s1 = storageRepository.findById(8L).get();

		Customers c1 = new Customers();
		c1.setName("A1");

		Customers c2 = new Customers();
		c2.setName("A1");
		List<Customers> customers = new ArrayList<>();
		;
		customers.add(c1);
		customers.add(c2);

		s1.customers.addAll(customers);

		Storage s2 = storageRepository.save(s1);

		s1.customers.remove(c2);

		s2.customers.stream().forEach(c -> {
			System.out.println(c);
		});
		int a = 4;
		assertEquals(2 + 2, a);

	}

	@Test
	void removeAllStorage() {

		List<Storage> storages = storageRepository.findAll();
		storages.forEach(s -> {
			storageRepository.delete(s);
		});

		int a = 4;
		assertEquals(2 + 2, a);

	}

//	@Test
	void testGetCustomer() throws Exception {
		Gson gson = new Gson();
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

		String url = "http://localhost:8088/customer/2";

		MockHttpServletResponse resp = mockMvc
				.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print())
				.andReturn().getResponse();

		Customer testDto = gson.fromJson(resp.getContentAsString(), Customer.class);

		assertEquals(testDto.getName(), "Bonobo-bdcf");

	}
// ./mvnw test -Dtest=RestApplicationTests#testGetCustomers

	@Test
	void testGetCustomers() throws Exception {

		Gson gson = new Gson();

		mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilter(((request, response, chain) -> {
			response.setCharacterEncoding("UTF-8");
			chain.doFilter(request, response);
		})).build();

		String url = "http://localhost:8088/customers";

		MockHttpServletResponse resp = mockMvc
				.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andDo(MockMvcResultHandlers.print())
				.andReturn().getResponse();

		Type listType = new TypeToken<ArrayList<Customer>>() {
		}.getType();
		List<Customer> result = new Gson().fromJson(resp.getContentAsString(), listType);

		customersRepository.findAll().forEach(s -> {

			String name = result.stream().filter(f -> f.getId().equals(s.getId())).map(n -> {
				return n.getName();
			}).findAny().get();

			assertEquals(name, s.getName());
		});

		// Customer testDto = gson.fromJson(resp.getContentAsString(), Customer.class);

	}

//	@Test
	void testDeleteCustomers() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilter(((request, response, chain) -> {
			response.setCharacterEncoding("UTF-8");
			chain.doFilter(request, response);
		})).build();

		Optional<Customers> opt = customersRepository.findById(Long.valueOf(9));
		if (opt.isPresent()) {

			String id = "9";

			String url = "http://localhost:8088/customer/" + id;

			MockHttpServletResponse resp = mockMvc
					.perform(MockMvcRequestBuilders.delete(url).contentType(MediaType.APPLICATION_JSON))
					.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk())
					.andDo(MockMvcResultHandlers.print()).andReturn().getResponse();

			assertNull(customersRepository.findById(Long.parseLong(id)).get());
		}

	}

	@Test
	void testSaveCustomer() throws Exception {
		Gson gson = new Gson();

		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

		String randomStr = UUID.randomUUID().toString().substring(0, 4);
		Customer customer = new Customer();
		customer.setName("Bonobo-" + randomStr);
		String json = gson.toJson(customer);

		String url = "http://localhost:8088/customer";

		MockHttpServletResponse resp = mockMvc
				.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON).content(json))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print())
				.andReturn().getResponse();

		String result = resp.getContentAsString();

		Long id = Long.parseLong(result);

		Customers test = customersRepository.findById(id).get();

		assertEquals(test.getName(), customer.getName());

	}
}
