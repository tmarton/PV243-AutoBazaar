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
public class VehicleModelDaoImpl extends BaseDaoImpl<VehicleModel, Long> implements VehicleModelDao, Serializable {

    @Override
    public List<VehicleModel> getAllVehicleModelsByBrand(VehicleBrand brand) {
        Query query = entityManager.createQuery("select vm.member From " + VehicleModel.class.getName() + " vm where vm.brand.id = :id");
        query.setParameter("id", brand.getId());
        return (List<VehicleModel>) query.getResultList();
        if (brand == null || brand.getId() == null)
            throw new IllegalArgumentException();
        
        /*List<VehicleModel> result = new ArrayList<>();
        for (VehicleModel v : getAll())
            if (v.getBrand().equals(brand))
                result.add(v);
        
        return result;*/
        
        TypedQuery<VehicleModel> query = entityManager.createNamedQuery("Vehicle.findByBrand", VehicleModel.class);
        query.setParameter("id", brand.getId());
        return query.getResultList();
    }
}
