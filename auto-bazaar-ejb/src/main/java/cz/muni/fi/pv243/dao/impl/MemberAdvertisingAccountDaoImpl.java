package cz.muni.fi.pv243.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import cz.muni.fi.pv243.dao.MemberAdvertisingAccountDao;
import cz.muni.fi.pv243.model.AdvertisingAccount;
import cz.muni.fi.pv243.model.Member;
import cz.muni.fi.pv243.model.MemberAdvertisingAccount;

/**
 * @author dubrouski
 * 
 */

@Stateless
public class MemberAdvertisingAccountDaoImpl extends
		BaseDaoImpl<MemberAdvertisingAccount, Long> implements
		MemberAdvertisingAccountDao {

	@Override
	public List<MemberAdvertisingAccount> getByAdvertisingAccount(
			AdvertisingAccount account) {
		if (account == null || account.getId() == null) {
			throw new IllegalArgumentException("account");
		}

		TypedQuery<MemberAdvertisingAccount> query = entityManager
				.createNamedQuery("MemberAdvertisingAccount.getByAccount",
						MemberAdvertisingAccount.class);
		query.setParameter("id", account.getId());
		return query.getResultList();
	}

	@Override
	public List<MemberAdvertisingAccount> getByMember(
			Member member) {
		if (member == null || member.getId() == null) {
			throw new IllegalArgumentException("account");
		}

		TypedQuery<MemberAdvertisingAccount> query = entityManager
				.createNamedQuery("MemberAdvertisingAccount.getByMember",
						MemberAdvertisingAccount.class);
		query.setParameter("id", member.getId());
		return query.getResultList();
	}

}
