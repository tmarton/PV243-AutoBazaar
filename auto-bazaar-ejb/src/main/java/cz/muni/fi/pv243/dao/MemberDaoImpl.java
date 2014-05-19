package cz.muni.fi.pv243.dao;

import cz.muni.fi.pv243.model.AdvertisingAccount;
import cz.muni.fi.pv243.model.Member;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
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
    public List<Member> getMembersBySellingCompany(AdvertisingAccount company) {
        return null;
    }
}
