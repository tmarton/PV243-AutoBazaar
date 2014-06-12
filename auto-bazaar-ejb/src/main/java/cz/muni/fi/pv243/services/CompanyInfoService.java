package cz.muni.fi.pv243.services;

import cz.muni.fi.pv243.dto.CompanyInfoDto;
import java.util.List;

/**
 *
 * @author Johny
 */
public interface CompanyInfoService {
    
    public CompanyInfoDto getByID(Long id);
    
    public List<CompanyInfoDto> getAll();  
    
    public void save(CompanyInfoDto entity);

    public void update(CompanyInfoDto entity);

    public void remove(CompanyInfoDto entity); 
    
}
