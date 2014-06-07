package cz.muni.fi.pv243.model;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: MemberAdvertisingAccount
 *
 * @author dubrouski
 */
@Entity
public class MemberAdvertisingAccount implements Serializable {

	private static final long serialVersionUID = 5L;
    
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_member")
	private Member member;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_advertising_account")
	private AdvertisingAccount advertisingAccount;
	
	//TODO	here will be placed info about member's role for the account (instead of description)
    //TODO this should be replaced with authority entity according to java ee security
	private String description;
	
	public MemberAdvertisingAccount() {
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
	public AdvertisingAccount getAccount() {
		return this.advertisingAccount;
	}

	public void setAccount(AdvertisingAccount account) {
		this.advertisingAccount = account;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
   
}
