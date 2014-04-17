package com.aspire.dao.common;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class CreatableEntity extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	protected Timestamp createdDate = new Timestamp(new Date().getTime());

	@Column(name = "created_date", insertable = true, updatable = false)
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
}
