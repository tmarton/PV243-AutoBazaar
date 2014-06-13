package cz.muni.fi.pv243.dao;

import java.util.List;

import cz.muni.fi.pv243.model.AdvertisingAccount;
import cz.muni.fi.pv243.model.Member;
import cz.muni.fi.pv243.model.MemberAdvertisingAccount;

/**
 * @author dubrouski
 * 
 */
public interface MemberAdvertisingAccountDao extends
		BaseDao<MemberAdvertisingAccount, Long> {
	public List<MemberAdvertisingAccount> getByAdvertisingAccount(
			AdvertisingAccount account);

	public List<MemberAdvertisingAccount> getByMember(
			Member member);
}
