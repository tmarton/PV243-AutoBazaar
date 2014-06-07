package cz.muni.fi.pv243.dao;

import cz.muni.fi.pv243.model.AdvertisingAccount;
import cz.muni.fi.pv243.model.CompanyInfo;

import javax.persistence.Query;

/**
 * Created by tmarton.
 */
public class CompanyInfoDaoImpl extends BaseDaoImpl<CompanyInfo, Long> implements CompanyInfoDao {

    @Override
    public CompanyInfo getCompanyInfoByAdvertisingAccount(AdvertisingAccount company) {
        Query query = entityManager.createQuery("select aa.companyInfo From " + AdvertisingAccount.class.getName() + " aa where aa.id = :id");
        query.setParameter("id", company.getId());
        return (CompanyInfo) query.getSingleResult();
    }
}
