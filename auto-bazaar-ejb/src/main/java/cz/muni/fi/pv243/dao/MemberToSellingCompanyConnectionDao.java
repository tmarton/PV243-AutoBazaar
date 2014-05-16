package cz.muni.fi.pv243.dao;

import cz.muni.fi.pv243.model.Member;
import cz.muni.fi.pv243.model.MemberToSellingCompanyConnection;
import cz.muni.fi.pv243.model.SellingCompany;

/**
 * @author dubrouski
 *
 */
public interface MemberToSellingCompanyConnectionDao {

	/**
     * 
     *
     * @param 
     *
     */
	public void createConnection(MemberToSellingCompanyConnection conn);

	/**
     * 
     *
     * @param 
     *
     */
	public void deleteConnection(MemberToSellingCompanyConnection conn);

	/**
     * 
     *
     * @param 
     *
     */
	public void updateConnection(MemberToSellingCompanyConnection conn);

	/**
     * Returns connection connecting given member and selling company.
     *
     * @param member member of connection 
     * @param company company of connection
     *
     */
	public void getConnectionByCompanyAndMember(Member member, SellingCompany company);
	
}
