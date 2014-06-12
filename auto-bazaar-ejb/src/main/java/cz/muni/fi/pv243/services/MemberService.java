package cz.muni.fi.pv243.services;

import cz.muni.fi.pv243.dto.MemberDto;
import java.util.List;

/**
 *
 * @author Johny
 */
public interface MemberService {
    
    public MemberDto getByID(Long id);
    
    public List<MemberDto> getAll();  
    
    public void save(MemberDto entity);

    public void update(MemberDto entity);

    public void remove(MemberDto entity); 
    
}
