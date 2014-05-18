package cz.muni.fi.pv243.dao;


import cz.muni.fi.pv243.model.AdvertisingAccount;
import cz.muni.fi.pv243.model.CompanyInfo;

/**
 * @author dubrouski
 *
 */
public interface CompanyInfoDao  extends BaseDao<CompanyInfo, Long> {

	/**
     * Returns company info of given selling company.
     *
     * @param company company that returned info belongs to.
     *
     */
	public CompanyInfo getCompanyInfoBySellingCompany(AdvertisingAccount company);


	
}
