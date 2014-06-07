package cz.muni.fi.pv243.dao;

import cz.muni.fi.pv243.model.AdvertisingAccount;
import cz.muni.fi.pv243.model.Member;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by tmarton.
 */
@Stateless
public class MemberDaoImpl extends  BaseDaoImpl<Member, Long> implements MemberDao {

    public MemberDaoImpl() {
        persistentClass = Member.class;
    }

    @Override
    public List<Member> getMembersByAdvertisingAccount(AdvertisingAccount company) {
        Query query = entityManager.createQuery("select cm.member From " + AdvertisingAccount.class.getName() + " aa join fetch aa.connectedMembers cm where aa.id = :id");
        query.setParameter("id", company.getId());
        return (List<Member>) query.getResultList();
    }
}
