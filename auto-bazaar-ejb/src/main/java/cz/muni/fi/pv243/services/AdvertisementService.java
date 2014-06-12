package cz.muni.fi.pv243.services;

import cz.muni.fi.pv243.dto.AdvertisementDto;
import java.util.List;

/**
 *
 * @author Johny
 */
public interface AdvertisementService {
    
    public AdvertisementDto getByID(Long id);
    
    public List<AdvertisementDto> getAll();  
    
    public void save(AdvertisementDto entity);

    public void update(AdvertisementDto entity);

    public void remove(AdvertisementDto entity); 
    
}
