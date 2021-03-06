package cz.muni.fi.pv243.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: MemberAdvertisingAccount
 * 
 * @author dubrouski
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "MemberAdvertisingAccount.getByAccount", 
				query = "SELECT m FROM MemberAdvertisingAccount m WHERE m.advertisingAccount.id = :id"),
		@NamedQuery(name = "MemberAdvertisingAccount.getByMember", 
				query = "SELECT m FROM MemberAdvertisingAccount m WHERE m.member.id = :id") })
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

	// TODO here will be placed info about member's role for the account
	// (instead of description)
	// TODO this should be replaced with authority entity according to java ee
	// security
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

	public AdvertisingAccount getAdvertisingAccount() {
		return advertisingAccount;
	}

	public void setAdvertisingAccount(AdvertisingAccount advertisingAccount) {
		this.advertisingAccount = advertisingAccount;
	}

	@Override
	public String toString() {
		return "MemberAdvertisingAccount{" + "id=" + id + ", member=" + member
				+ ", advertisingAccount=" + advertisingAccount
				+ ", description=" + description + '}';
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 47 * hash + Objects.hashCode(this.id);
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
		final MemberAdvertisingAccount other = (MemberAdvertisingAccount) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}

}
