package com.aspire.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.aspire.dao.common.DeletableEntity;

/**
 * The persistent class for the Item database table.
 * 
 */
@Entity
@Table(name = "Item")
public class Item extends DeletableEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public Item() {
	}

	private String descriptions;

	private String name;

	// private Set<Color> colors = new HashSet<Color>();
	private Color color;

	@Column(name = "descriptions")
	public String getDescriptions() {
		return this.descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToOne(mappedBy = "item", fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, orphanRemoval = true)
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	// // @OneToMany
	// // @Cascade({ CascadeType.ALL })
	// // @JoinColumn(name = "item_id")
	// // @LazyCollection(LazyCollectionOption.FALSE)
	// @OneToMany(mappedBy = "item", fetch = FetchType.EAGER, cascade = {
	// CascadeType.ALL }, targetEntity = Color.class, orphanRemoval = true)
	// public Set<Color> getColors() {
	// return colors;
	// }
	//
	// public void setColors(Set<Color> colors) {
	// this.colors = colors;
	// }

}