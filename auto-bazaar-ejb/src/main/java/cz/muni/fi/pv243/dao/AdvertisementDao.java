/**
 * 
 */
package cz.muni.fi.pv243.dao;

import java.util.List;
import cz.muni.fi.pv243.model.Advertisement;
import cz.muni.fi.pv243.model.AdvertisingAccount;

/**
 * @author dubrouski
 *
 */
public interface AdvertisementDao extends BaseDao<Advertisement, Long> {

	/**
     * Returns all ads by given selling company.
     *
     */
	public List<Advertisement> getAdvertisementsByAdvertisingAccount(AdvertisingAccount advertisingAccount);
	
	
	
}
