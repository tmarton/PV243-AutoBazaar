package cz.muni.fi.pv243.test;

import java.util.*;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.naming.NamingException;

import cz.muni.fi.pv243.dao.MemberDao;
import cz.muni.fi.pv243.model.MemberAdvertisingAccount;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import cz.muni.fi.pv243.model.Member;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class MemberDaoTest {

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
    private MemberDao memberDao;

    @Before
    public void setUp() {

    }

    @After
    public void teraDown() {
        memberDao.removeAll();
    }

    @Test
    public void createCorrectMember() {
        Member m = getPreparedMember();
        assertNull(m.getId());

        memberDao.save(m);

        Member m2 = memberDao.getByID(m.getId());
        assertMemberDeepEquals(m, m2);
    }

    @Test
    public void createEmployeeWithSetId(){
        Member m = getPreparedMember();
        m.setId(1L);
        try{
            memberDao.save(m);
            fail("Created Member passed with set Id");
        }catch(Exception ex){
            //ok
        }
    }

    @Test
    public void createEmployeeNull(){
        try{
            memberDao.save(null);
            fail("Create Member passed with null argument");
        }catch(Exception ex){
            // ok
        }
    }

    @Test
    public void createCreatedEmployee(){
        Member member = getPreparedMember();

        memberDao.save(member);
        try{
            memberDao.save(member);
            fail(member.toString() + " allready in database");
        }catch(Exception ex){
            // ok
        }
    }

    @Test
    public void createEmployWithWrongAttributes() throws NamingException {
        Member member = new Member();
        member.setEmail("test@test.cz");
        try{
            memberDao.save(member);
            fail(member.toString() + " passed without name and phone");
        }catch(Exception ex){
            //ok
        }

        member = new Member();
        member.setName("Test");
        try{
            memberDao.save(member);
            fail(member.toString() + " passed without email and phone");
        }catch(Exception ex){
            //ok
        }

        member = new Member();
        member.setPhoneNumber("666 666 666");
        try{
            memberDao.save(member);
            fail(member.toString() + " passed without email and name");
        }catch(Exception ex){
            //ok
        }
    }

    @Test
    public void retrieveMemberOK(){
        memberDao.removeAll();
        assertNull(memberDao.getByID(1L));

        Member member = getPreparedMember();

        memberDao.save(member);

        Member retMemeber = memberDao.getByID(member.getId());
        //deep equals
        assertMemberDeepEquals(member,retMemeber);
    }

    @Test
    public void retrieveMamberFail() throws NamingException{
        try{
            memberDao.getByID(null);
            fail();
        }
        catch(Exception ex){
            //ok
        }
    }

    @Test
    public void updateEmployeeOK(){
        Member member = getPreparedMember();

        memberDao.save(member);

        member.setEmail("test2@test.cz");
        member.setName("testX");
        member.setPhoneNumber("+420 666 666 666");

        memberDao.update(member);

        Member retMember = memberDao.getByID(member.getId());

        assertMemberDeepEquals(member,retMember);
    }

    @Test
    public void updateEmployeeFail() throws NamingException{
        Member member = new Member();

        try{
            memberDao.update(null);
            fail("update with null");
        }catch(Exception ex){
            //ok
        }

        try{
            memberDao.update(member);
            fail("update non consistent entity");
        }catch(Exception ex){
            //ok
        }
    }

    @Test
    public void deleteEmplyeeOK(){

        Member member = getPreparedMember();

        memberDao.save(member);

        memberDao.remove(member);

        assertNull(memberDao.getByID(member.getId()));
    }

    @Test
    public void deleteEmployeeFail() throws NamingException{
        try{
            memberDao.remove(null);
            fail();
        }catch(Exception ex){
            // ok
        }

        Member member = new Member();
        try{
            memberDao.remove(member);
            fail();
        }catch(Exception ex){
            //ok
        }

        member.setId(new Long(11));
        try{
            // not in DB
            memberDao.remove(member);
            fail("delete entity not in DB");
        }catch(Exception ex){
            // ok
        }
    }

    @Test
    public void retrieveAllEmployees() throws NamingException{
        memberDao.removeAll();
        assertTrue(memberDao.getAll().isEmpty());

        Member member1 = newMember("testX", "test@test.cz", "605666666");
        Member member2 = newMember("testY", "test2@test.cz", "+420 605666666");
        Member member3 = newMember("testZ", "test3@test.cz", "+421605666666");

        memberDao.save(member1);
        memberDao.save(member2);
        memberDao.save(member3);

        List<Member> expected = Arrays.asList(member1,member2,member3);

        List<Member> actual = memberDao.getAll();

        assertMemberCollectionDeepEquals(expected, actual);
    }

    private Member newMember(String name, String email, String phone) {
        Member member = new Member();
        member.setName(name);
        member.setEmail(email);
        member.setPhoneNumber(phone);
        return member;
    }

    private static Member getPreparedMember() {
        Member m = new Member();
        m.setName("test");
        m.setEmail("test@test.cz");
        m.setPhoneNumber("666 666 666");
        m.setAdvertisingAccounts(new ArrayList<MemberAdvertisingAccount>());
        return m;
    }

    private static void assertMemberDeepEquals(Member m1, Member m2) {
        assertEquals(m1.getId(), m2.getId());
        assertEquals(m1.getName(), m2.getName());
        assertEquals(m1.getEmail(), m2.getEmail());
        assertEquals(m1.getPhoneNumber(), m2.getPhoneNumber());

    }

    static void assertMemberCollectionDeepEquals(List<Member> expected, List<Member> actual) {

        assertEquals(expected.size(), actual.size());
        Collections.sort(expected,MemberKeyComparator);
        Collections.sort(actual,MemberKeyComparator);
        for (int i = 0; i < expected.size(); i++) {
            assertMemberDeepEquals(expected.get(i), actual.get(i));
        }
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

    private static Comparator<Member> MemberKeyComparator = new Comparator<Member>() {

        @Override
        public int compare(Member o1, Member o2) {
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
