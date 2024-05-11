package com.contactspreadsheet.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "contacts")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@Column(nullable = false, length = 30)
	private String email;
	
	@Column(nullable = false, length = 100)
	private String address;
	
	@Column(nullable = false)
	@Temporal(value = TemporalType.DATE)
	private LocalDate birthDate;
	
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getAddress() {
		return address;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
}
