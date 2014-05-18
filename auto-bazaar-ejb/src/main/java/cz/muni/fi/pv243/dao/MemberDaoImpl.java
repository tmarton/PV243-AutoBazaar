package cz.muni.fi.pv243.dao;

import cz.muni.fi.pv243.model.AdvertisingAccount;
import cz.muni.fi.pv243.model.Member;

import java.util.List;

/**
 * Created by tmarton.
 */
public class MemberDaoImpl extends  BaseDaoImpl<Member, Long> implements MemberDao {

    @Override
    public List<Member> getMembersBySellingCompany(AdvertisingAccount company) {
        return null;
    }
}
