package de.mathema.training.jpa.kunde;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vertrag implements Serializable {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String vertragsnummer;
	
	@ManyToOne
	private Kunde kunde;

}
