package cz.muni.fi.pv243.services;

import cz.muni.fi.pv243.dto.VehicleBrandDto;
import java.util.List;

/**
 *
 * @author Johny
 */
public interface VehicleBrandService {
    
    public VehicleBrandDto getByID(Long id);
    
    public List<VehicleBrandDto> getAll();  
    
    public void save(VehicleBrandDto entity);

    public void update(VehicleBrandDto entity);

    public void remove(VehicleBrandDto entity); 
    
}
