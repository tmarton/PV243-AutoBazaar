package cz.muni.fi.pv243.services.impl;

import cz.muni.fi.pv243.dao.VehiclePhotoDao;
import cz.muni.fi.pv243.dto.AdvertisementDto;
import cz.muni.fi.pv243.dto.VehiclePhotoDto;
import cz.muni.fi.pv243.model.Advertisement;
import cz.muni.fi.pv243.model.VehiclePhoto;
import cz.muni.fi.pv243.services.VehiclePhotoService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.inject.Inject;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

/**
 *
 * @author Johny
 */
@Stateless
@TransactionManagement
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class VehiclePhotoServiceImpl implements VehiclePhotoService {

    @Inject
    private VehiclePhotoDao dao;
    
    private final Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();

    @Override
    public VehiclePhotoDto getByID(Long id) {
        VehiclePhoto m = dao.getByID(id);
        return m == null ? null : mapper.map(m, VehiclePhotoDto.class);
    }

    @Override
    public List<VehiclePhotoDto> getAll() {
        List<VehiclePhoto> all = dao.getAll();
        List<VehiclePhotoDto> res = new ArrayList<>(all.size());
        for (VehiclePhoto m : all)
            res.add(mapper.map(m, VehiclePhotoDto.class));
        return res;
    }

    @Override
    public void save(VehiclePhotoDto entity) {
        VehiclePhoto m = mapper.map(entity, VehiclePhoto.class);
        dao.save(m);
        entity.setId(m.getId());        
    }

    @Override
    public void update(VehiclePhotoDto entity) {
        dao.update(mapper.map(entity, VehiclePhoto.class));
    }

    @Override
    public void remove(VehiclePhotoDto entity) {
        dao.remove(mapper.map(entity, VehiclePhoto.class));
        entity.setId(null);
    }

    @Override
    public List<VehiclePhotoDto> getAllVehiclePhotosByAdvertisement(AdvertisementDto advert) {
        List<VehiclePhoto> all = dao.getAllVehiclePhotosByAdvertisement(mapper.map(advert, Advertisement.class));
        List<VehiclePhotoDto> res = new ArrayList<>(all.size());
        for (VehiclePhoto a : all)
            res.add(mapper.map(a, VehiclePhotoDto.class));
        return res;
    }
}
