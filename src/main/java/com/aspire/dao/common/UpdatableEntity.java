package com.aspire.dao.common;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class UpdatableEntity extends CreatableEntity {
	private static final long serialVersionUID = 1L;
	protected Timestamp modifiedDate = new Timestamp(new Date().getTime());

	@Column(name = "modified_date")
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}
