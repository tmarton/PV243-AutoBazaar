package cz.muni.fi.pv243.model;

import java.util.List;
import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Member implements Serializable {
	/** Default value included to remove warning. Remove or modify at will. **/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Size(min = 1, max = 50)
	@Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
	private String name;
	
	@NotNull
	@NotEmpty
	@Email
	private String email;

	@NotNull
	@Size(min = 9, max = 16)
    @Pattern(regexp = "/^(\\+420|\\+421)? ?[0-9]{3} ?[0-9]{3} ?[0-9]{3}$/", message = "wrong phone number format")
	@Column(name = "phone_number")
	private String phoneNumber;

	@OneToMany(mappedBy="member", fetch = FetchType.LAZY)
	private List<MemberAdvertisingAccount> advertisingAccounts;
	
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

    public List<MemberAdvertisingAccount> getAdvertisingAccounts() {
        return advertisingAccounts;
    }

    public void setAdvertisingAccounts(List<MemberAdvertisingAccount> advertisingAccounts) {
        this.advertisingAccounts = advertisingAccounts;
    }
}