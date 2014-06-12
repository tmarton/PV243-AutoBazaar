package cz.muni.fi.pv243.dto;

import java.io.Serializable;
import java.util.Objects;

public class MemberAdvertisingAccountDto implements Serializable {

	private static final long serialVersionUID = 5L;
    
	private Long id;
	private MemberDto member;
	private AdvertisingAccountDto advertisingAccount;
	private String description;

    public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public MemberDto getMember() {
		return this.member;
	}

	public void setMember(MemberDto member) {
		this.member = member;
	}   
	public AdvertisingAccountDto getAccount() {
		return this.advertisingAccount;
	}

	public void setAccount(AdvertisingAccountDto account) {
		this.advertisingAccount = account;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    @Override
    public String toString() {
        return "MemberAdvertisingAccountDto{" + "id=" + id + ", member=" + member + ", advertisingAccount=" + advertisingAccount + ", description=" + description + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final MemberAdvertisingAccountDto other = (MemberAdvertisingAccountDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
   
    
}
