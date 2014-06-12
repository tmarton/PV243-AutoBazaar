package cz.muni.fi.pv243.model;

import java.io.Serializable;
import java.lang.Long;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Account
 *
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "AdvertisingAccount.getByMember", 
    		query = "SELECT aa.advertisingAccount FROM MemberAdvertisingAccount aa WHERE aa.member.id = :id")
})        
public class AdvertisingAccount implements Serializable {

	private static final long serialVersionUID = 2L;
	   
	@Id
	@GeneratedValue
	private Long id;
		
	@Column(name="is_active")
	private boolean isActive;
	
	/* if account type is set to company account, then company info is set */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_company_info")
	private CompanyInfo companyInfo;

	@OneToMany(mappedBy = "advertisingAccount", fetch=FetchType.EAGER)
	private List<MemberAdvertisingAccount> connectedMembers = new ArrayList<>();
	
	public AdvertisingAccount() {
		super();
	}   
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

	public CompanyInfo getCompanyInfo() {
		return companyInfo;
	}
	public void setCompanyInfo(CompanyInfo companyInfo) {
		this.companyInfo = companyInfo;
	}
	public List<MemberAdvertisingAccount> getConnectedMembers() {
		return connectedMembers;
	}
	public void setConnectedMembers(List<MemberAdvertisingAccount> connectedMembers) {
		this.connectedMembers = connectedMembers;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
   
}
