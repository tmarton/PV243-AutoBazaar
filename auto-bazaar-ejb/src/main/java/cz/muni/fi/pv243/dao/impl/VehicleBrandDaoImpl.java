package cz.muni.fi.pv243.dao.impl;

import cz.muni.fi.pv243.dao.VehicleBrandDao;
import cz.muni.fi.pv243.model.VehicleBrand;

import java.io.Serializable;
import javax.ejb.Stateless;

/**
 * Created by Johny.
 */
@Stateless
public class VehicleBrandDaoImpl extends BaseDaoImpl<VehicleBrand, Long> implements VehicleBrandDao, Serializable {

}
