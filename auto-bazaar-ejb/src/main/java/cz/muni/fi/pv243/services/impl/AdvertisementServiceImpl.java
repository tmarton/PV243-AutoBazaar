package cz.muni.fi.pv243.services.impl;

import cz.muni.fi.pv243.dao.AdvertisementDao;
import cz.muni.fi.pv243.dto.AdvertisementDto;
import cz.muni.fi.pv243.model.Advertisement;
import cz.muni.fi.pv243.services.AdvertisementService;
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
public class AdvertisementServiceImpl implements AdvertisementService {

    @Inject
    private AdvertisementDao dao;
    
    private final Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();

    @Override
    public AdvertisementDto getByID(Long id) {
        Advertisement m = dao.getByID(id);
        return m == null ? null : mapper.map(m, AdvertisementDto.class);
    }

    @Override
    public List<AdvertisementDto> getAll() {
        List<Advertisement> all = dao.getAll();
        List<AdvertisementDto> res = new ArrayList<>(all.size());
        for (Advertisement m : all)
            res.add(mapper.map(m, AdvertisementDto.class));
        return res;
    }

    @Override
    public void save(AdvertisementDto entity) {
        Advertisement m = mapper.map(entity, Advertisement.class);
        dao.save(m);
        entity.setId(m.getId());        
    }

    @Override
    public void update(AdvertisementDto entity) {
        dao.update(mapper.map(entity, Advertisement.class));
    }

    @Override
    public void remove(AdvertisementDto entity) {
        dao.remove(mapper.map(entity, Advertisement.class));
        entity.setId(null);
    }
}
