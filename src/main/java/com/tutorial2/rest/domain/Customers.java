package com.tutorial2.rest.domain;

import org.hibernate.annotations.SortNatural;

import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customers implements Comparable<Customers> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

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

	@SortNatural
	@Column(name = "phone", unique = true, nullable = false)
	@OneToMany(cascade = CascadeType.ALL)
	public SortedSet<Phones> phones = new TreeSet<>();


	@Override
	public String toString() {
		return "Customers [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int compareTo(Customers arg0) {

		return name.compareTo(arg0.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customers other = (Customers) obj;
		return Objects.equals(name, other.name);
	}

}
