package com.aspire.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.aspire.dao.common.AbstractEntity;

/**
 * The persistent class for the Color database table.
 * 
 */
@Entity
@NamedQuery(name = "User.findByName", query = "SELECT u FROM User u")
@Table(name = "user")
public class User extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public User() {
	}

	private String username;
	private String password;
	private String role;

	@Column(name = "USERNAME")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "ROLE")
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}