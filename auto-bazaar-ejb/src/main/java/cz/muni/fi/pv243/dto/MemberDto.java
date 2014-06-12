package cz.muni.fi.pv243.dto;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class MemberDto implements Serializable {

    private static final long serialVersionUID = 4L;

	private Long id;
	private String name;
	private String email;
	private String phoneNumber;
	private List<MemberAdvertisingAccountDto> advertisingAccounts = new ArrayList<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

    public List<MemberAdvertisingAccountDto> getAdvertisingAccounts() {
        return advertisingAccounts;
    }

    public void setAdvertisingAccounts(List<MemberAdvertisingAccountDto> advertisingAccounts) {
        this.advertisingAccounts = advertisingAccounts;
    }

    @Override
    public String toString() {
        return "MemberDto{" + "id=" + id + ", name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + ", advertisingAccounts=" + advertisingAccounts + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final MemberDto other = (MemberDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}