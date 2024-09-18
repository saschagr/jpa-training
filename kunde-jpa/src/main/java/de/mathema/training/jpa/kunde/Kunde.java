package de.mathema.training.jpa.kunde;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Past;
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
public class Kunde implements Serializable, ChangeableData {
	
	@Id
	@GeneratedValue
	@Getter
	private Long id;

	private String name;

	@Enumerated(EnumType.STRING)
	private Anrede anrede;
	
	@Temporal(TemporalType.DATE)
	@Past
	private Date geburtsdatum;
	
	@ElementCollection
	@OrderColumn
	private List<Telefon> telefons = new ArrayList<>();
	
	@Version
	@Getter
	private Integer version;
	
	@OneToMany(
			mappedBy = "kunde", 
			fetch = FetchType.EAGER, 
			cascade = CascadeType.ALL)
	private List<Vertrag> vertraege = new ArrayList<>();
	
	@Builder.Default
	@Getter
	@Embedded
	private ChangeData changeData = new ChangeData();;

	public void addVertrag(Vertrag vertrag) {
		if (vertraege == null) {
			vertraege = new ArrayList<>();
		}
		vertraege.add(vertrag);
	}
}
