package cz.muni.fi.pv243.dao;

import java.util.List;

import cz.muni.fi.pv243.model.AdvertisingAccount;
import cz.muni.fi.pv243.model.Member;

/**
 * @author dubrouski
 *
 */
public interface MemberDao extends BaseDao<Member, Long> {


	/**
     * Returns all members that are connected to 
     *
     * @param company
     *
     */
	public List<Member> getMembersBySellingCompany(AdvertisingAccount company);
	
}
