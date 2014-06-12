package cz.muni.fi.pv243.dao.impl;

import cz.muni.fi.pv243.dao.MemberDao;
import cz.muni.fi.pv243.model.AdvertisingAccount;
import cz.muni.fi.pv243.model.Member;

import javax.ejb.Stateless;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 * Created by tmarton.
 */
@Stateless
public class MemberDaoImpl extends  BaseDaoImpl<Member, Long> implements MemberDao {

    @Override
    public List<Member> getMembersByAdvertisingAccount(AdvertisingAccount company) {
        if (company == null || company.getId() == null)
            throw new IllegalArgumentException();
        
        TypedQuery<Member> query = entityManager.createNamedQuery("Member.getByAccount", Member.class);
        query.setParameter("id", company.getId());
        return query.getResultList();
    }
}
