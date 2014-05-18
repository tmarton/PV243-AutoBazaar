package cz.muni.fi.pv243.dao;

import java.util.List;

import cz.muni.fi.pv243.model.Advertisement;
import cz.muni.fi.pv243.model.VehiclePhoto;

/**
 * @author dubrouski
 *
 */
public interface VehiclePhotoDao extends BaseDao<VehiclePhoto, Long>{


	/**
     * Returns all photos associated with given advertisement.
     *
     * @param advert
     *
     */
	public List<VehiclePhoto> getAllVehiclePhotosByAdvertisement(Advertisement advert);

}
