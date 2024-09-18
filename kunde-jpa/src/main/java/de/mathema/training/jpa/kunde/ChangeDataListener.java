package de.mathema.training.jpa.kunde;

import java.util.Date;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class ChangeDataListener {

	@PrePersist
	public void prePersist(Object value) {
		if (value instanceof ChangeableData changeableData) {
			changeableData.getChangeData().setCreated(new Date());
			changeableData.getChangeData().setCreatedBy(this.getCurrentUser());
		}
		this.preUpdate(value);
	}

	private String getCurrentUser() {
		return "unknown";
	}

	@PreUpdate
	public void preUpdate(Object value) {
//		try {
//			Method method = value.getClass().getMethod("getChangeData");
//			method.setAccessible(true);
//			ChangeData changeData = (ChangeData) method.invoke(value);
//		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			e.printStackTrace();
//		}

		if (value instanceof ChangeableData changeableData) {
			changeableData.getChangeData().setUpdated(new Date());
			changeableData.getChangeData().setUpdatedBy(this.getCurrentUser());
		}
	}

}
