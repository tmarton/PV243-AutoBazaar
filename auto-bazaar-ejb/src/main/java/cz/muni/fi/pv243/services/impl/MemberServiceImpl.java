package cz.muni.fi.pv243.services.impl;

import cz.muni.fi.pv243.dao.MemberDao;
import cz.muni.fi.pv243.dto.AdvertisingAccountDto;
import cz.muni.fi.pv243.dto.MemberDto;
import cz.muni.fi.pv243.model.AdvertisingAccount;
import cz.muni.fi.pv243.model.Member;
import cz.muni.fi.pv243.services.MemberService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.inject.Inject;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

/**
 *
 * @author Johny
 */
@Stateless
@TransactionManagement
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class MemberServiceImpl implements MemberService {

    @Inject
    private MemberDao dao;
    
    private final Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();

    @Override
    public MemberDto getByID(Long id) {
        Member m = dao.getByID(id);
        return m == null ? null : mapper.map(m, MemberDto.class);
    }

    @Override
    public List<MemberDto> getAll() {
        List<Member> all = dao.getAll();
        List<MemberDto> res = new ArrayList<>(all.size());
        for (Member m : all)
            res.add(mapper.map(m, MemberDto.class));
        return res;
    }

    @Override
    public void save(MemberDto entity) {
        Member m = mapper.map(entity, Member.class);
        dao.save(m);
        entity.setId(m.getId());        
    }

    @Override
    public void update(MemberDto entity) {
        dao.update(mapper.map(entity, Member.class));
    }

    @Override
    public void remove(MemberDto entity) {
        dao.remove(mapper.map(entity, Member.class));
        entity.setId(null);
    }

    @Override
    public List<MemberDto> getMembersByAccount(AdvertisingAccountDto account) {
        List<Member> all = dao.getMembersByAdvertisingAccount(mapper.map(account, AdvertisingAccount.class));
        List<MemberDto> res = new ArrayList<>(all.size());
        for (Member m : all)
            res.add(mapper.map(m, MemberDto.class));
        return res;
    }
}
