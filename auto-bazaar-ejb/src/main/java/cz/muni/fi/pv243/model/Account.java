package cz.muni.fi.pv243.model;

import java.io.Serializable;
import java.lang.Long;
import java.util.List;

import javax.persistence.*;

import cz.muni.fi.pv243.enums.AccountType;

/**
 * Entity implementation class for Entity: Account
 *
 */
@Entity

public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne
	private Member member;
	
	@Column(name="is_active")
	private boolean isActive;
	
	@Enumerated(EnumType.STRING)
	private AccountType type;	
	
	/* if account type is set to company account, then company info is set */
	@Column(name="company_info")
	private CompanyInfo companyInfo;

	@OneToMany(mappedBy="member")
	private List<MemberToAccountConnection> connectedMembers;
	
	public Account() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	
	public Member getMember() {
		return member;
	}
	
	public void setMember(Member member) {
		this.member = member;
	}
	
	public boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}   
	public AccountType getType() {
		return this.type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}
	public CompanyInfo getCompanyInfo() {
		return companyInfo;
	}
	public void setCompanyInfo(CompanyInfo companyInfo) {
		this.companyInfo = companyInfo;
	}
	public List<MemberToAccountConnection> getConnectedMembers() {
		return connectedMembers;
	}
	public void setConnectedMembers(List<MemberToAccountConnection> connectedMembers) {
		this.connectedMembers = connectedMembers;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
   
}
