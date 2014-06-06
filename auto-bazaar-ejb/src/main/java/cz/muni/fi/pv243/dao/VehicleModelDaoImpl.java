package cz.muni.fi.pv243.dao;

import cz.muni.fi.pv243.model.VehicleBrand;
import cz.muni.fi.pv243.model.VehicleModel;

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

    public VehicleModelDaoImpl() {
        persistentClass = VehicleModel.class;
    }

    @Override
    public VehicleModel getByID(Long id) {
        if (id == null || id < 0)
            throw new IllegalArgumentException("id cannot be null or lesser than zero");
        
        return super.getByID(id);
    }

    @Override
    public void save(VehicleModel entity) {
        if (entity == null)
            throw new IllegalArgumentException("entity cannot be null");
        
        if (entity.getId() != null)
            throw new IllegalArgumentException("id must be null - entity already exists?");
        
        if (entity.getName() == null || entity.getName().isEmpty())
            throw new IllegalArgumentException("name cannot be null nor empty");  
        
        super.save(entity);
    }

    @Override
    public void update(VehicleModel entity) {
        if (entity == null)
            throw new IllegalArgumentException("entity cannot be null");
        
        if (entity.getId() == null)
            throw new IllegalArgumentException("id cannot be null - entity doesn't exist?");
        
        if (entity.getName() == null || entity.getName().isEmpty())
            throw new IllegalArgumentException("name cannot be null nor empty");         
        
        super.update(entity);
    }
    
    @Override
    public void remove(VehicleModel entity) {
        if (entity == null)
            throw new IllegalArgumentException("entity cannot be null");
        
        if (entity.getId() == null)
            throw new IllegalArgumentException("id cannot be null - removing non existing entity?");        
        
        super.remove(entity);
    }   
    
    
    @Override
    public List<VehicleModel> getAllVehicleModelsByBrand(VehicleBrand brand) {
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
