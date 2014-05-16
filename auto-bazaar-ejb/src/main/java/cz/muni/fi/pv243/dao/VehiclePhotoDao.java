package cz.muni.fi.pv243.dao;

import java.util.List;

import cz.muni.fi.pv243.model.Advertisement;
import cz.muni.fi.pv243.model.VehicleBrand;
import cz.muni.fi.pv243.model.VehiclePhoto;

/**
 * @author dubrouski
 *
 */
public interface VehiclePhotoDao {

	/**
     * 
     *
     * @param photo photo to be created.
     *
     */
	public void createVehiclePhoto(VehiclePhoto photo);

	/**
     * 
     *
     * @param photo photo to be deleted.
     *
     */
	public void deleteVehiclePhoto(VehiclePhoto photo);

	/**
     * 
     *
     * @param photo photo to be updated.
     *
     */
	public void updateVehiclePhoto(VehiclePhoto photo);

	/**
     * Returns all photos associated with given advertisement.
     *
     * @param 
     *
     */
	public List<VehiclePhoto> getAllVehiclePhotosByAdvertisement(Advertisement advert);
	
	/**
     * Returns photo by its id.
     *
     * @param id id of photo to be returned.
     *
     */
	public VehiclePhoto getVehiclePhotoById(Long id);
}
