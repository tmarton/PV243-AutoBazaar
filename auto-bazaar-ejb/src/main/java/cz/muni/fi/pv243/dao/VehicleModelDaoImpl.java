package cz.muni.fi.pv243.dao;

import cz.muni.fi.pv243.model.VehicleBrand;
import cz.muni.fi.pv243.model.VehicleModel;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 * Created by tmarton.
 */
@Stateless
public class VehicleModelDaoImpl extends BaseDaoImpl<VehicleModel, Long> implements VehicleModelDao, Serializable {

    @Override
    public List<VehicleModel> getAllVehicleModelsByBrand(VehicleBrand brand) {
        if (brand == null || brand.getId() == null)
            throw new IllegalArgumentException();
        
        TypedQuery<VehicleModel> query = entityManager.createNamedQuery("VehicleModel.findByBrand", VehicleModel.class);
        query.setParameter("id", brand.getId());
        return query.getResultList();
    }
}
