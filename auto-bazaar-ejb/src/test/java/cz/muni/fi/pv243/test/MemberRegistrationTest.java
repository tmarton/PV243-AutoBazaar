package cz.muni.fi.pv243.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import cz.muni.fi.pv243.dao.MemberDao;
import cz.muni.fi.pv243.model.MemberAdvertisingAccount;
import cz.muni.fi.pv243.util.Dao;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import cz.muni.fi.pv243.model.Member;

@RunWith(Arquillian.class)
public class MemberRegistrationTest {

   @Deployment
   public static Archive<?> createTestArchive() {

       return ShrinkWrap.create(WebArchive.class, "test.war")
            .addPackage("cz.muni.fi.pv243.model")
            .addPackage("cz.muni.fi.pv243.enums")
            .addPackage("cz.muni.fi.pv243.dao")
            .addPackage("cz.muni.fi.pv243.util")
            .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml").resolve("joda-time:joda-time-hibernate").withTransitivity().asFile())
            .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml").resolve("joda-time:joda-time").withTransitivity().asFile())
            .addAsResource("META-INF/persistence.xml", ArchivePaths.create("META-INF/persistence.xml"))
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
   }


   @Inject
   Logger log;

    @Inject
    private MemberDao memberDao;

    @Test
    public void CreateNoMember(){

        List<Member> ms = memberDao.getAll();
        assertEquals(ms.size(), 0);

    }

    @Test
    public void createCorrectVehicle() {
        Member m = new Member();
        m.setName("test");
        m.setEmail("test@test.cz");
        m.setPhoneNumber("666 666 666");
        m.setAdvertisingAccounts(new ArrayList<MemberAdvertisingAccount>());
        assertNull(m.getId());

        memberDao.save(m);

        Member m2 = memberDao.getByID(m.getId());
        assertMemberDeepEquals(m, m2);
    }

    private static void assertMemberDeepEquals(Member m1, Member m2) {
        assertEquals(m1.getId(), m2.getId());
        assertEquals(m1.getName(), m2.getName());
        assertEquals(m1.getEmail(), m2.getEmail());
        assertEquals(m1.getPhoneNumber(), m2.getPhoneNumber());

    }

    private static void assertMemberAdvertisingAccountDeepEquals(List<MemberAdvertisingAccount> l1 , List<MemberAdvertisingAccount> l2) {
        assertEquals(l1.size(), l2.size());
        Collections.sort(l1,MemberAdvertisingAccountComparator);
        Collections.sort(l2,MemberAdvertisingAccountComparator);
        for(int i = 0; i < l2.size(); i++) {
            // should be replaced with assert deep copy
            assertEquals(l1.get(i).getId(),l2.get(i).getId());
        }

    }

    private static Comparator<MemberAdvertisingAccount> MemberAdvertisingAccountComparator = new Comparator<MemberAdvertisingAccount>() {
        @Override
        public int compare(MemberAdvertisingAccount o1, MemberAdvertisingAccount o2) {
            Long k1 = o1.getId();
            Long k2 = o2.getId();
            if (k1 == null && k2 == null) {
                return 0;
            } else if (k1 == null && k2 != null) {
                return -1;
            } else if (k1 != null && k2 == null) {
                return 1;
            } else {
                return k1.compareTo(k2);
            }
        }
    };
   
}
