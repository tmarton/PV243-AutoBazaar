package cz.muni.fi.pv243.dao;

import java.util.List;

import cz.muni.fi.pv243.model.VehicleBrand;

/**
 * @author dubrouski
 *
 */
public interface VehicleBrandDao {

	/**
     * 
     *
     * @param brand brand to save
     *
     */
	public void createVehicleBrand(VehicleBrand brand);

	/**
     * 
     *
     * @param brand brand to delete
     *
     */
	public void deleteVehicleBrand(VehicleBrand brand);

	/**
     * 
     *
     * @param brand brand to update
     *
     */
	public void updateVehicleBrand(VehicleBrand brand);

	/**
     * 
     */
	public List<VehicleBrand> getAllVehicleBrands();

	/**
     * Return brand by given id.
     *
     * @param id id of brand to be returned
     *
     */
	public VehicleBrand getVehicleBrandById(Long id);
}
