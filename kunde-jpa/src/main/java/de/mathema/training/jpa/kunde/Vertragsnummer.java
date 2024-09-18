package de.mathema.training.jpa.kunde;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vertragsnummer implements Serializable {
	
	private String nummer;

}
