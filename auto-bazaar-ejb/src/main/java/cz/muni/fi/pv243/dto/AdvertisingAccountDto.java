package cz.muni.fi.pv243.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdvertisingAccountDto implements Serializable {

	private static final long serialVersionUID = 2L;
	   
	private Long id;
	private boolean isActive;
	private CompanyInfoDto companyInfo;
	private List<MemberAdvertisingAccountDto> connectedMembers = new ArrayList<>();

    public Long getId() {
		return this.id;
	}

	public void setId(Long id) { 
		this.id = id;
	}   
	
	public boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}   

	public CompanyInfoDto getCompanyInfo() {
		return companyInfo;
	}
	public void setCompanyInfo(CompanyInfoDto companyInfo) {
		this.companyInfo = companyInfo;
	}
	public List<MemberAdvertisingAccountDto> getConnectedMembers() {
		return connectedMembers;
	}
	public void setConnectedMembers(List<MemberAdvertisingAccountDto> connectedMembers) {
		this.connectedMembers = connectedMembers;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

    @Override
    public String toString() {
        return "AdvertisingAccountDto{" + "id=" + id + ", isActive=" + isActive + ", companyInfo=" + companyInfo + ", connectedMembersCount=" + connectedMembers.size() + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final AdvertisingAccountDto other = (AdvertisingAccountDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
	
    
}
