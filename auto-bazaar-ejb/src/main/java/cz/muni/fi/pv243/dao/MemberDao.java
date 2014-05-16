package cz.muni.fi.pv243.dao;

import java.util.List;

import cz.muni.fi.pv243.model.CompanyInfo;
import cz.muni.fi.pv243.model.Member;
import cz.muni.fi.pv243.model.SellingCompany;

/**
 * @author dubrouski
 *
 */
public interface MemberDao {

	/**
     * 
     *
     * @param 
     *
     */
	public void createMember(Member member);

	/**
     * 
     *
     * @param 
     *
     */
	public void deleteMember(Member member);

	/**
     * 
     *
     * @param 
     *
     */
	public void updateMember(Member member);

	/**
     * 
     *
     * @param 
     *
     */
	public CompanyInfo getMemberById(Long id);

	/**
     * 
     *
     * @param 
     *
     */
	public List<Member> getAllMembers();

	/**
     * Returns all members that are connected to 
     *
     * @param 
     *
     */
	public List<Member> getMembersBySellingCompany(SellingCompany company);
	
}
