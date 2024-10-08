package de.mathema.training.jpa.kunde;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kunde {
	
	@Id
	@GeneratedValue
	@Getter
	private Long id;
	
	private String name;
}
