package com.tutorial2.rest.domain;

import javax.persistence.*;

@Entity
@Table(name = "goods")
public class Goods {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Double price;
	@Column(name = "Наименование")
	private String Наименование;

	public Goods() {
		super();
	}

	public Goods(Long id, String name, String Наименование, Double price) {
		super();
		this.id = id;
		this.Наименование = Наименование;
		this.name = name;
		this.price = price;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setNaim(String Наименование) {
		this.Наименование = Наименование;
	}

	public String getNaim() {
		return Наименование;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPrice() {
		return price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
