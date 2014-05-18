package cz.muni.fi.pv243.model;

import java.io.Serializable;
import java.lang.Long;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

/**
 * Entity implementation class for Entity: CompanyInfo
 * 
 * @author dubrouski
 */
@Entity
public class CompanyInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	   
	@Id
	private Long id;
	
	@Column(name = "zip_code")
	private int zipCode;
	
	private String country;
	
	private String city;
	
	private String street; 

	@NotNull
    @Size(min = 9, max = 16)
    @Pattern(regexp = "/^(\\+420|\\+421)? ?[0-9]{3} ?[0-9]{3} ?[0-9]{3}$/", message = "wrong phone number format")
	private String phone;

	@Email
	private String email;

	public CompanyInfo() {
		super();
	}   
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
   
	
}
