package cz.muni.fi.pv243.services;

import java.util.List;

import cz.muni.fi.pv243.dto.AdvertisingAccountDto;
import cz.muni.fi.pv243.dto.MemberDto;

/**
 * 
 * @author dubrouski
 */
public interface AdvertisingAccountService {
	public AdvertisingAccountDto getByID(Long id);

	public List<AdvertisingAccountDto> getAll();

	public void save(AdvertisingAccountDto entity);

	public void update(AdvertisingAccountDto entity);

	public void remove(AdvertisingAccountDto entity);

	public List<AdvertisingAccountDto> getAccountsByMember(
			MemberDto member);
}
