package cz.muni.fi.pv243.dao;

import cz.muni.fi.pv243.model.VehicleBrand;
import cz.muni.fi.pv243.model.VehicleModel;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * Created by tmarton.
 */
public class VehicleModelDaoImpl extends BaseDaoImpl<VehicleModel, Long> implements VehicleModelDao, Serializable {

    @Override
    public List<VehicleModel> getAllVehicleModelsByBrand(VehicleBrand brand) {
        Query query = entityManager.createQuery("select vm.member From " + VehicleModel.class.getName() + " vm where vm.brand.id = :id");
        query.setParameter("id", brand.getId());
        return (List<VehicleModel>) query.getResultList();
    }
}
