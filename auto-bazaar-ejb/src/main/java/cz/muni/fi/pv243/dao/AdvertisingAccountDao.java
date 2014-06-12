package cz.muni.fi.pv243.dao;

import java.util.List;

import cz.muni.fi.pv243.model.AdvertisingAccount;
import cz.muni.fi.pv243.model.Member;

/**
 * @author dubrouski
 * 
 */
public interface AdvertisingAccountDao extends
		BaseDao<AdvertisingAccount, Long> {
	/**
	 * @return Returns all advertising accounts by given member.
	 */
	public List<AdvertisingAccount> getAdvertisingAccountsByMember(
			Member member);
}
