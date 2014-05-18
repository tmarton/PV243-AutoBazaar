package cz.muni.fi.pv243.dao;

import java.util.List;
import cz.muni.fi.pv243.model.VehicleBrand;
import cz.muni.fi.pv243.model.VehicleModel;

/**
 * @author dubrouski
 *
 */
public interface VehicleModelDao extends BaseDao<VehicleModel, Long> {

	/**
     * Returns all model, that belong to given brand.
     *
     * @param brand brand that returned models belong to.
     *
     */
	public List<VehicleModel> getAllVehicleModelsByBrand(VehicleBrand brand);
	
}
