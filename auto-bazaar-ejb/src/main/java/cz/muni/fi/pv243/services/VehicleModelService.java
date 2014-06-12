package cz.muni.fi.pv243.services;

import cz.muni.fi.pv243.dao.VehicleModelDao;
import cz.muni.fi.pv243.dto.VehicleModelDto;
import java.util.List;

/**
 *
 * @author Johny
 */
public interface VehicleModelService {
    
    public VehicleModelDto getByID(Long id);
    
    public List<VehicleModelDto> getAll();  
    
    public void save(VehicleModelDto entity);

    public void update(VehicleModelDto entity);

    public void remove(VehicleModelDto entity); 
    
    /**
     * for testing injection
     * @param dao 
     */
    public void setDao(VehicleModelDao dao);
    
    public VehicleModelDao getDao();
    
}
