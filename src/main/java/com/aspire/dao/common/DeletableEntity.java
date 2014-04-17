package com.aspire.dao.common;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class DeletableEntity extends UpdatableEntity {
	private static final long serialVersionUID = 1L;
	protected String deleted;

	@Column(name = "deleted")
	public String getDeleted() {
		return this.deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
}
