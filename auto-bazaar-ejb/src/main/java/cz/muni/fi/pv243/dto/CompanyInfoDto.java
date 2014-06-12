package cz.muni.fi.pv243.dto;

import java.io.Serializable;
import java.util.Objects;

public class CompanyInfoDto implements Serializable {
	
	private static final long serialVersionUID = 3L;
	   
	private Long id;
	private int zipCode;
	private String country;
	private String city;
	private String street; 
	private String phone;
	private String email;

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

    @Override
    public String toString() {
        return "CompanyInfoDto{" + "id=" + id + ", zipCode=" + zipCode + ", country=" + country + ", city=" + city + ", street=" + street + ", phone=" + phone + ", email=" + email + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CompanyInfoDto other = (CompanyInfoDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
