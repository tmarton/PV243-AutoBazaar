package cz.muni.fi.pv243.dao;

import cz.muni.fi.pv243.model.Advertisement;
import cz.muni.fi.pv243.model.AdvertisingAccount;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by tmarton.
 */
public class AdvertisementDaoImpl extends BaseDaoImpl<Advertisement, Long> implements AdvertisementDao {

    public List<Advertisement> getAdvertisementsByAdvertisingAccount( AdvertisingAccount advertisingAccount){
        Query query = entityManager.createQuery("From " + Advertisement.class.getName() + " a where a.advertisingAccount.id = :id");
        query.setParameter("id", advertisingAccount.getId());
        return (List<Advertisement>) query.getResultList();
    }
}
