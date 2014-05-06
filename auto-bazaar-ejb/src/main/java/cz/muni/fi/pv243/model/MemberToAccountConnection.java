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

public class MemberToAccountConnection implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Member member;
	
	@ManyToOne
	private Account account;
	
	//TODO	here will be placed info about member's role for the account (instead of description)
	private String description;
	
	private static final long serialVersionUID = 1L;

	public MemberToAccountConnection() {
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
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
   
}
