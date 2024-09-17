package de.mathema.training.jpa.kunde;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OrderBy;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Kunde implements Serializable {
	
	@Id
	@GeneratedValue
	@Getter
	private Long id;

	private String name;

	@Enumerated(EnumType.STRING)
	private Anrede anrede;
	
	@Temporal(TemporalType.DATE)
	private Date geburtsdatum;
	
	@ElementCollection
	@OrderColumn
	private List<Telefon> telefons = new ArrayList<>();
	
	@Version
	@Getter
	private Integer version;
}
