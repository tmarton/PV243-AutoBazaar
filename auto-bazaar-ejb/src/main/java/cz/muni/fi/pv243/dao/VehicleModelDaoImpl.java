package cz.muni.fi.pv243.dao;

import cz.muni.fi.pv243.model.VehicleBrand;
import cz.muni.fi.pv243.model.VehicleModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tmarton.
 */
public class VehicleModelDaoImpl extends BaseDaoImpl<VehicleModel, Long> implements VehicleModelDao, Serializable {

    @Override
    public List<VehicleModel> getAllVehicleModelsByBrand(VehicleBrand brand) {
        return null;
    }
}
