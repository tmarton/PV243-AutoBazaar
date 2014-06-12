package cz.muni.fi.pv243.services.impl;

import cz.muni.fi.pv243.dao.VehicleModelDao;
import cz.muni.fi.pv243.dto.VehicleModelDto;
import cz.muni.fi.pv243.model.VehicleModel;
import cz.muni.fi.pv243.services.VehicleModelService;
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
public class VehicleModelServiceImpl implements VehicleModelService {

    @Inject
    private VehicleModelDao dao;
    
    private final Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();

    @Override
    public VehicleModelDto getByID(Long id) {
        VehicleModel m = dao.getByID(id);
        return m == null ? null : mapper.map(m, VehicleModelDto.class);
    }

    @Override
    public List<VehicleModelDto> getAll() {
        List<VehicleModel> all = dao.getAll();
        List<VehicleModelDto> res = new ArrayList<>(all.size());
        for (VehicleModel m : all)
            res.add(mapper.map(m, VehicleModelDto.class));
        return res;
    }

    @Override
    public void save(VehicleModelDto entity) {
        VehicleModel m = mapper.map(entity, VehicleModel.class);
        dao.save(m);
        entity.setId(m.getId());        
    }

    @Override
    public void update(VehicleModelDto entity) {
        dao.update(mapper.map(entity, VehicleModel.class));
    }

    @Override
    public void remove(VehicleModelDto entity) {
        dao.remove(mapper.map(entity, VehicleModel.class));
        entity.setId(null);
    }

    @Override
    public void setDao(VehicleModelDao dao) {
        this.dao = dao;
    }

    @Override
    public VehicleModelDao getDao() {
        return dao;
    }
}
