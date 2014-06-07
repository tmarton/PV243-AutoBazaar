package cz.muni.fi.pv243.dao;

import cz.muni.fi.pv243.model.Advertisement;
import cz.muni.fi.pv243.model.VehiclePhoto;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by tmarton.
 */
public class VehiclePhotoDaoImpl extends BaseDaoImpl<VehiclePhoto, Long> implements VehiclePhotoDao {

    @Override
    public List<VehiclePhoto> getAllVehiclePhotosByAdvertisement(Advertisement advert) {
        Query query = entityManager.createQuery("select a.vehiclePhotos From " + Advertisement.class.getName() + " a where a.id = :id");
        query.setParameter("id", advert.getId());
        return (List<VehiclePhoto>) query.getResultList();
    }
}
