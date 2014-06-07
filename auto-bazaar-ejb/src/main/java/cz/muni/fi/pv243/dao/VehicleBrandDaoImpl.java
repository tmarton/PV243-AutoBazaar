package cz.muni.fi.pv243.dao;

import cz.muni.fi.pv243.model.VehicleBrand;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 * Created by Johny.
 */
@Stateless
public class VehicleBrandDaoImpl extends BaseDaoImpl<VehicleBrand, Long> implements VehicleBrandDao, Serializable {

    public VehicleBrandDaoImpl() {
        persistentClass = VehicleBrand.class;
    }

}
