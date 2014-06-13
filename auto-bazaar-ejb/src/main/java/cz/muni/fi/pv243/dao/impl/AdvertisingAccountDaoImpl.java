package cz.muni.fi.pv243.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import cz.muni.fi.pv243.dao.AdvertisingAccountDao;
import cz.muni.fi.pv243.model.AdvertisingAccount;
import cz.muni.fi.pv243.model.Member;
/**
 * @author dubrouski
 *
 */
@Stateless
public class AdvertisingAccountDaoImpl extends
		BaseDaoImpl<AdvertisingAccount, Long> implements AdvertisingAccountDao {

	@Override
	public List<AdvertisingAccount> getAdvertisingAccountsByMember(Member member) {
		if (member == null || member.getId() == null) {
			throw new IllegalArgumentException("member");
		}

		TypedQuery<AdvertisingAccount> query = entityManager.createNamedQuery(
				"AdvertisingAccount.getByMember", AdvertisingAccount.class);
		query.setParameter("id", member.getId());
		return query.getResultList();
	}

}
