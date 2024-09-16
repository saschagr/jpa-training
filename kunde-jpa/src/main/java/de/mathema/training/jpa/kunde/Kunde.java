package de.mathema.training.jpa.kunde;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Kunde implements Serializable {
	
	@Id
	@GeneratedValue
	private Long id;

	private String name;

	public Kunde() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}
}
