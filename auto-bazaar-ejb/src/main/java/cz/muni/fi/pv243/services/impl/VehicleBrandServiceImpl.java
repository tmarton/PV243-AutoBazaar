package cz.muni.fi.pv243.services.impl;

import cz.muni.fi.pv243.dao.VehicleBrandDao;
import cz.muni.fi.pv243.dto.VehicleBrandDto;
import cz.muni.fi.pv243.model.VehicleBrand;
import cz.muni.fi.pv243.services.VehicleBrandService;
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
public class VehicleBrandServiceImpl implements VehicleBrandService {

    @Inject
    private VehicleBrandDao dao;
    
    private final Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();

    @Override
    public VehicleBrandDto getByID(Long id) {
        VehicleBrand m = dao.getByID(id);
        return m == null ? null : mapper.map(m, VehicleBrandDto.class);
    }

    @Override
    public List<VehicleBrandDto> getAll() {
        List<VehicleBrand> all = dao.getAll();
        List<VehicleBrandDto> res = new ArrayList<>(all.size());
        for (VehicleBrand m : all)
            res.add(mapper.map(m, VehicleBrandDto.class));
        return res;
    }

    @Override
    public void save(VehicleBrandDto entity) {
        VehicleBrand m = mapper.map(entity, VehicleBrand.class);
        dao.save(m);
        entity.setId(m.getId());        
    }

    @Override
    public void update(VehicleBrandDto entity) {
        dao.update(mapper.map(entity, VehicleBrand.class));
    }

    @Override
    public void remove(VehicleBrandDto entity) {
        dao.remove(mapper.map(entity, VehicleBrand.class));
        entity.setId(null);
    }
}
