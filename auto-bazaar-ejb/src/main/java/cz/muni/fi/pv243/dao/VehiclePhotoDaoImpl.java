package cz.muni.fi.pv243.dao;

import cz.muni.fi.pv243.model.Advertisement;
import cz.muni.fi.pv243.model.VehiclePhoto;

import java.util.List;

/**
 * Created by tmarton.
 */
public class VehiclePhotoDaoImpl extends BaseDaoImpl<VehiclePhoto, Long> implements VehiclePhotoDao {

    @Override
    public List<VehiclePhoto> getAllVehiclePhotosByAdvertisement(Advertisement advert) {
        return null;
    }
}
