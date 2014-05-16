package cz.muni.fi.pv243.dao;

import java.util.List;
import cz.muni.fi.pv243.model.VehicleBrand;
import cz.muni.fi.pv243.model.VehicleModel;

/**
 * @author dubrouski
 *
 */
public interface VehicleModelDao {

	/**
     * 
     *
     * @param model to be created.
     *
     */
	public void createVehicleModel(VehicleModel model);

	/**
     * 
     *
     * @param model to be deleted.
     *
     */
	public void deleteVehicleModel(VehicleModel model);

	/**
     * 
     *
     * @param model to be updated.
     *
     */
	public void updateVehicleModel(VehicleModel model);

	/**
     * Returns all model, that belong to given brand.
     *
     * @param brand brand that returned models belong to.
     *
     */
	public List<VehicleModel> getAllVehicleModelsByBrand(VehicleBrand brand);

	/**
     * Returns model by id.
     *
     * @param id id of model to be returned.
     *
     */
	public VehicleModel getVehicleModelById(Long id);	
	
}
