package cz.muni.fi.pv243.dao;


import cz.muni.fi.pv243.model.CompanyInfo;
import cz.muni.fi.pv243.model.SellingCompany;

/**
 * @author dubrouski
 *
 */
public interface CompanyInfoDao {

	/**
     * Stores given company info to db.
     *
     * @param info companyInfo to be stored.
     *
     */
	public void createCompanyInfo(CompanyInfo info);

	/**
     * Deletes given company info if exists.
     *
     * @param info companyInfo to be deleted.
     *
     */
	public void deleteCompanyInfo(CompanyInfo info);

	/**
     * Updates given company info.
     *
     * @param info companyInfo to be updated.
     *
     */
	public void updateCompanyInfo(CompanyInfo info);

	/**
     * Returns company info of given selling company.
     *
     * @param company company that returned info belongs to.
     *
     */
	public CompanyInfo getCompanyInfoBySellingCompany(SellingCompany company);

	/**
     * Returns company info by id.
     *
     * @param id of company info to be returned.
     *
     */
	public CompanyInfo getCompanyInfoById(Long id);
	
}
