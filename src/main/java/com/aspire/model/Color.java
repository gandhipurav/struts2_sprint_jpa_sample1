package com.aspire.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.aspire.dao.common.AbstractEntity;

/**
 * The persistent class for the Color database table.
 * 
 */
@Entity
@Table(name = "Color")
public class Color extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public Color() {
	}

	@Column(name = "color")
	private String color;

	// @Column(name = "item_id")
	private Item item;

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	// @ManyToOne
	// @JoinColumn(name = "item_id", updatable = false, insertable = false)
	@OneToOne
	@JoinColumn(name = "item_id")
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}