package de.mathema.training.jpa.kunde;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vertragsnummer {
	
	private String nummer;

}
