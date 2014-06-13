package cz.muni.fi.pv243.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.inject.Inject;

import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

import cz.muni.fi.pv243.dao.AdvertisementDao;
import cz.muni.fi.pv243.dao.AdvertisingAccountDao;
import cz.muni.fi.pv243.dto.AdvertisementDto;
import cz.muni.fi.pv243.dto.AdvertisingAccountDto;
import cz.muni.fi.pv243.dto.MemberDto;
import cz.muni.fi.pv243.model.Advertisement;
import cz.muni.fi.pv243.model.AdvertisingAccount;
import cz.muni.fi.pv243.model.Member;
import cz.muni.fi.pv243.services.AdvertisingAccountService;

/**
 * 
 * @author dubrouski
 */
@Stateless
@TransactionManagement
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AdvertisingAccountServiceImpl implements AdvertisingAccountService {

	@Inject
	private AdvertisingAccountDao dao;

	private final Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();

	@Override
	public AdvertisingAccountDto getByID(Long id) {
		AdvertisingAccount a = dao.getByID(id);
        return a == null ? null : mapper.map(a, AdvertisingAccountDto.class);
	}

	@Override
	public List<AdvertisingAccountDto> getAll() {
		List<AdvertisingAccount> all = dao.getAll();
        List<AdvertisingAccountDto> res = new ArrayList<>(all.size());
        for (AdvertisingAccount m : all)
            res.add(mapper.map(m, AdvertisingAccountDto.class));
        return res;
	}

	@Override
	public void save(AdvertisingAccountDto entity) {
		AdvertisingAccount m = mapper.map(entity, AdvertisingAccount.class);
        dao.save(m);
        entity.setId(m.getId()); 
	}

	@Override
	public void update(AdvertisingAccountDto entity) {
		dao.update(mapper.map(entity, AdvertisingAccount.class));

	}

	@Override
	public void remove(AdvertisingAccountDto entity) {
		dao.remove(mapper.map(entity, AdvertisingAccount.class));
        entity.setId(null);

	}

	@Override
	public List<AdvertisingAccountDto> getAccountsByMember(
			MemberDto member) {
		List<AdvertisingAccount> all = dao.getAdvertisingAccountsByMember(mapper.map(member, Member.class));
        List<AdvertisingAccountDto> res = new ArrayList<>(all.size());
        for (AdvertisingAccount a : all)
            res.add(mapper.map(a, AdvertisingAccountDto.class));
        return res;
	}

}
