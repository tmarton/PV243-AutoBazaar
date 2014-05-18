package cz.muni.fi.pv243.dao;

import cz.muni.fi.pv243.model.AdvertisingAccount;
import cz.muni.fi.pv243.model.CompanyInfo;

/**
 * Created by tmarton.
 */
public class CompanyInfoDaoImpl extends BaseDaoImpl<CompanyInfo, Long> implements CompanyInfoDao {

    @Override
    public CompanyInfo getCompanyInfoBySellingCompany(AdvertisingAccount company) {
        return null;
    }
}
