package cz.muni.fi.pv243.dto;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class MemberDto implements Serializable {

    private static final long serialVersionUID = 4L;

    private Long id;

    @Size(min = 1, max = 50)
    @Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
    private String name;

    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "wrong email address format")
    private String email;

    @Size(min = 9, max = 16)
    @Pattern(regexp = "^(\\+420|\\+421)? ?[0-9]{3} ?[0-9]{3} ?[0-9]{3}$", message = "wrong phone number format")
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