/**
 * 
 */
package cz.muni.fi.pv243.dao;

import java.util.List;
import cz.muni.fi.pv243.model.Advertisement;

/**
 * @author dubrouski
 *
 */
public interface AdvertisementDao {
	
	/**
     * Stores given ad to db.
     *
     * @param ad advertisement to save.
     *
     */
	public void createAdcertisement(Advertisement ad);

	/**
     * Deletes given ad from db.
     *
     * @param ad advertisement to delete.
     *
     */
	public void deleteAdvertisement(Advertisement ad);

	/**
     * Updates given ad.
     *
     * @param ad ad to be updated.
     *
     */
	public void updateAdvertisement(Advertisement ad);

	/**
     * Returns all stored ads.
     *
     */
	public List<Advertisement> getAllAdvertisements();

	/**
     * Returns ad with given id.
     *
     * @param id of ad to be returned.
     *
     */
	public Advertisement getAdvertisementById(Long id);

	/**
     * Returns all ads by given selling company.
     *
     */
	public List<Advertisement> getAdvertisementsBySellingCompany();
	
	
	
}
