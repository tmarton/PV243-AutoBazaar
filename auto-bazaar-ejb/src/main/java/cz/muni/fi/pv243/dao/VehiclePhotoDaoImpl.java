package cz.muni.fi.pv243.dao;

import cz.muni.fi.pv243.model.Advertisement;
import cz.muni.fi.pv243.model.VehicleModel;
import cz.muni.fi.pv243.model.VehiclePhoto;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 * Created by tmarton.
 */
@Stateless
public class VehiclePhotoDaoImpl extends BaseDaoImpl<VehiclePhoto, Long> implements VehiclePhotoDao {

    @Override
    public List<VehiclePhoto> getAllVehiclePhotosByAdvertisement(Advertisement advert) {
        if (advert == null || advert.getId() == null)
            throw new IllegalArgumentException();
        
        TypedQuery<VehiclePhoto> query = entityManager.createNamedQuery("VehiclePhoto.getByAdvertisement", VehiclePhoto.class);
        query.setParameter("id", advert.getId());
        return query.getResultList();
    }
}
