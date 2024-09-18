package de.mathema.training.jpa.kunde;

import java.io.Serializable;

import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
//@Data
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vertrag implements Serializable, ChangeableData {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@VertragsnummerValidation
	@Convert(
			converter = VertragsnummerPrefixConverter.class
	)
	private Vertragsnummer vertragsnummer;
	
	@ManyToOne
	private Kunde kunde;
	
	@Builder.Default
	@Getter
	@Embedded
	private ChangeData changeData = new ChangeData();;
	
	@Version
	@Getter
	private Integer version;



}
