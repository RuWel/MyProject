package com.urs.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Email;

import org.hibernate.validator.constraints.Length;


@Entity
@Table(name="Users")
public class UserDTO {
	@Id
	@GeneratedValue
	@Column(name="USER_ID")
	private long id;
	
	@NotEmpty
	@Length(max = 50)
	@Column(name="USER_NAME")
	private String name;
	
	@NotEmpty
	@Length(max = 150)
	@Column(name="USER_ADDRESS")
	private String address;
	
	@Email
	@NotEmpty
	@Length(max = 80)
	@Column(name="USER_EMAIL")
	private String email;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}