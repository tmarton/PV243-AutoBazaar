package cz.muni.fi.pv243.services.impl;

import cz.muni.fi.pv243.dao.CompanyInfoDao;
import cz.muni.fi.pv243.dto.AdvertisingAccountDto;
import cz.muni.fi.pv243.dto.CompanyInfoDto;
import cz.muni.fi.pv243.model.AdvertisingAccount;
import cz.muni.fi.pv243.model.CompanyInfo;
import cz.muni.fi.pv243.services.CompanyInfoService;
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
public class CompanyInfoServiceImpl implements CompanyInfoService {

    @Inject
    private CompanyInfoDao dao;
    
    private final Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();

    @Override
    public CompanyInfoDto getByID(Long id) {
        CompanyInfo m = dao.getByID(id);
        return m == null ? null : mapper.map(m, CompanyInfoDto.class);
    }

    @Override
    public List<CompanyInfoDto> getAll() {
        List<CompanyInfo> all = dao.getAll();
        List<CompanyInfoDto> res = new ArrayList<>(all.size());
        for (CompanyInfo m : all)
            res.add(mapper.map(m, CompanyInfoDto.class));
        return res;
    }

    @Override
    public void save(CompanyInfoDto entity) {
        CompanyInfo m = mapper.map(entity, CompanyInfo.class);
        dao.save(m);
        entity.setId(m.getId());        
    }

    @Override
    public void update(CompanyInfoDto entity) {
        dao.update(mapper.map(entity, CompanyInfo.class));
    }

    @Override
    public void remove(CompanyInfoDto entity) {
        dao.remove(mapper.map(entity, CompanyInfo.class));
        entity.setId(null);
    }

    @Override
    public CompanyInfoDto getCompanyInfoByAccount(AdvertisingAccountDto account) {
        CompanyInfo info = dao.getCompanyInfoByAdvertisingAccount(mapper.map(account, AdvertisingAccount.class));
        return info == null ? null : mapper.map(info, CompanyInfoDto.class);
    }
}
