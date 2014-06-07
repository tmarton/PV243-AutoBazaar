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

    private static final String IMG_URL_REGEX = "(?i).+\\.(jpg|jpeg|png|gif|bmp|tga)";
    
    @Override
    public void save(VehiclePhoto entity) {
        if (entity == null || entity.getUrl() == null)
            throw new IllegalArgumentException();
        
        if (!entity.getUrl().getFile().matches(IMG_URL_REGEX))
            throw new IllegalArgumentException("URL must point to an image");
            
        super.save(entity);
    }

    @Override
    public void update(VehiclePhoto entity) {
        if (entity == null || entity.getUrl() == null)
            throw new IllegalArgumentException();
        
        if (!entity.getUrl().getFile().matches(IMG_URL_REGEX))
            throw new IllegalArgumentException("URL must point to an image");
        
        super.update(entity);
    }
    
    @Override
    public List<VehiclePhoto> getAllVehiclePhotosByAdvertisement(Advertisement advert) {
        if (advert == null || advert.getId() == null)
            throw new IllegalArgumentException();
        
        TypedQuery<VehiclePhoto> query = entityManager.createNamedQuery("VehiclePhoto.getByAdvertisement", VehiclePhoto.class);
        query.setParameter("id", advert.getId());
        return query.getResultList();
    }
}
