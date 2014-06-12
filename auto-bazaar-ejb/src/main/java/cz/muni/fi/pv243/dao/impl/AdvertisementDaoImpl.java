package cz.muni.fi.pv243.dao.impl;

import cz.muni.fi.pv243.dao.AdvertisementDao;
import cz.muni.fi.pv243.model.Advertisement;
import cz.muni.fi.pv243.model.AdvertisingAccount;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 * Created by tmarton.
 */
@Stateless
public class AdvertisementDaoImpl extends BaseDaoImpl<Advertisement, Long> implements AdvertisementDao {

    public List<Advertisement> getAdvertisementsByAdvertisingAccount(AdvertisingAccount advertisingAccount) {
        if (advertisingAccount == null || advertisingAccount.getId() == null)
            throw new IllegalArgumentException();
        
        TypedQuery<Advertisement> query = entityManager.createNamedQuery("Advertisement.getByAccount", Advertisement.class);
        query.setParameter("id", advertisingAccount.getId());
        return query.getResultList();
    }
}
