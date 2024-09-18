package de.mathema.training.jpa.kunde;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Embeddable
public class ChangeData implements Serializable {
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;
	
	private String updatedBy;

	protected Date getCreated() {
		return created;
	}

	protected void setCreated(Date created) {
		this.created = created;
	}

	protected String getCreatedBy() {
		return createdBy;
	}

	protected void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	protected Date getUpdated() {
		return updated;
	}

	protected void setUpdated(Date updated) {
		this.updated = updated;
	}

	protected String getUpdatedBy() {
		return updatedBy;
	}

	protected void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
}
