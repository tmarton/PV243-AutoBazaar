package cz.muni.fi.pv243.model;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: MemberToAccountConnection
 *
 */
@Entity

public class MemberToSellingCompanyConnection implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Member member;
	
	@ManyToOne
	private SellingCompany sellingCompany;
	
	//TODO	here will be placed info about member's role for the account (instead of description)
	private String description;
	
	private static final long serialVersionUID = 1L;

	public MemberToSellingCompanyConnection() { 
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}   
	public SellingCompany getAccount() {
		return this.sellingCompany;
	}

	public void setAccount(SellingCompany account) {
		this.sellingCompany = account;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
   
}
