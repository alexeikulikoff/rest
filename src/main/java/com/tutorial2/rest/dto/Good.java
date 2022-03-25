package com.tutorial2.rest.dto;

public class Good {

	private Long id;
	private String name;
	private Double price;
	private String Наименование;

	public Good() {
		super();
	}

	public Good(Long id, String name, String Наименование, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.Наименование = Наименование;
		this.price = price;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNaim() {
		return Наименование;
	}

	public void setNaim(String Наименование) {
		this.Наименование = Наименование;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}


}
