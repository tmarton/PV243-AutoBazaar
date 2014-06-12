package cz.muni.fi.pv243.services;

import cz.muni.fi.pv243.dto.VehiclePhotoDto;
import java.util.List;

/**
 *
 * @author Johny
 */
public interface VehiclePhotoService {
    
    public VehiclePhotoDto getByID(Long id);
    
    public List<VehiclePhotoDto> getAll();  
    
    public void save(VehiclePhotoDto entity);

    public void update(VehiclePhotoDto entity);

    public void remove(VehiclePhotoDto entity); 
    
}
