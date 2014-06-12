package cz.muni.fi.pv243.dao.impl;

import cz.muni.fi.pv243.dao.CompanyInfoDao;
import cz.muni.fi.pv243.model.AdvertisingAccount;
import cz.muni.fi.pv243.model.CompanyInfo;
import javax.ejb.Stateless;

import javax.persistence.TypedQuery;

/**
 * Created by tmarton.
 */
@Stateless
public class CompanyInfoDaoImpl extends BaseDaoImpl<CompanyInfo, Long> implements CompanyInfoDao {

    @Override
    public CompanyInfo getCompanyInfoByAdvertisingAccount(AdvertisingAccount company) {
        if (company == null || company.getId() == null)
            throw new IllegalArgumentException();
        
        TypedQuery<CompanyInfo> query = entityManager.createNamedQuery("CompanyInfo.getByAccount", CompanyInfo.class);
        query.setParameter("id", company.getId());
        return query.getSingleResult();
    }
}
