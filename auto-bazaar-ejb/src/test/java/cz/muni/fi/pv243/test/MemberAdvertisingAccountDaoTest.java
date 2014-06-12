package cz.muni.fi.pv243.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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

import cz.muni.fi.pv243.dao.AdvertisingAccountDao;
import cz.muni.fi.pv243.dao.MemberAdvertisingAccountDao;
import cz.muni.fi.pv243.dao.MemberDao;
import cz.muni.fi.pv243.model.AdvertisingAccount;
import cz.muni.fi.pv243.model.Member;
import cz.muni.fi.pv243.model.MemberAdvertisingAccount;

@RunWith(Arquillian.class)
public class MemberAdvertisingAccountDaoTest {

	@Inject
	private MemberDao memberDao;

	@Inject
	private AdvertisingAccountDao adAccountDao;

	@Inject
	private MemberAdvertisingAccountDao memberAdAccountDao;

	private Member member;
	private Member anotherMember;
	private AdvertisingAccount account;
	private AdvertisingAccount anotherAccount;
	private MemberAdvertisingAccount maa1;
	private MemberAdvertisingAccount maa2;
	
	
	@Before
	public void setUp() {
		prepareData();
	}

	@After
	public void tearDown() {
		memberAdAccountDao.removeAll();
		memberDao.removeAll();
		adAccountDao.removeAll();		
	}

	@Deployment
	public static Archive<?> createTestArchive() {

		return ShrinkWrap
				.create(WebArchive.class, "test_member_advertising_account.war")
				.addPackage("cz.muni.fi.pv243.model")
				.addPackage("cz.muni.fi.pv243.enums")
				.addPackage("cz.muni.fi.pv243.dao")
				.addPackage("cz.muni.fi.pv243.dao.impl")
				.addPackage("cz.muni.fi.pv243.util")
				.addAsLibraries(
						Maven.resolver().loadPomFromFile("pom.xml")
								.resolve("org.jadira.usertype:usertype.core")
								.withTransitivity().asFile())
				.addAsLibraries(
						Maven.resolver().loadPomFromFile("pom.xml")
								.resolve("joda-time:joda-time")
								.withTransitivity().asFile())
				.addAsResource("META-INF/persistence.xml",
						ArchivePaths.create("META-INF/persistence.xml"))
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void getByMemberTest() {		

		List<MemberAdvertisingAccount> byMember1 = memberAdAccountDao
				.getByMember(member);
		assertEquals(byMember1.size(), 1);
		assertEquals(byMember1.get(0).getId(), maa1.getId());

		List<MemberAdvertisingAccount> byMember2 = memberAdAccountDao
				.getByMember(anotherMember);
		assertEquals(byMember2.size(), 1);
		assertEquals(byMember2.get(0).getId(), maa2.getId());
	}

	@Test
	public void getByAccountTest() {
		
		List<MemberAdvertisingAccount> byAccount1 = memberAdAccountDao
				.getByAdvertisingAccount(account);
		assertEquals(byAccount1.size(), 1);
		assertEquals(byAccount1.get(0).getId(), maa1.getId());

		List<MemberAdvertisingAccount> byAccount2 = memberAdAccountDao
				.getByAdvertisingAccount(anotherAccount);
		assertEquals(byAccount2.size(), 1);
		assertEquals(byAccount2.get(0).getId(), maa2.getId());
	}
	
	private void prepareData(){
		member = getPreparedMember();
		memberDao.save(member);

		anotherMember = getPreparedMember();
		anotherMember.setEmail("anotheremail@site.com");
		memberDao.save(anotherMember);

		account = new AdvertisingAccount();
		adAccountDao.save(account);

		anotherAccount = new AdvertisingAccount();
		adAccountDao.save(anotherAccount);

		maa1 = new MemberAdvertisingAccount();
		maa1.setAdvertisingAccount(account);
		maa1.setDescription("superadmin");
		maa1.setMember(member);
		memberAdAccountDao.save(maa1);

		maa2 = new MemberAdvertisingAccount();
		maa2.setAdvertisingAccount(anotherAccount);
		maa2.setDescription("superadmin");
		maa2.setMember(anotherMember);
		memberAdAccountDao.save(maa2);
	}

	private static Member getPreparedMember() {
		Member m = new Member();
		m.setName("test");
		m.setEmail("test@test.cz");
		m.setPhoneNumber("666 666 666");
		m.setAdvertisingAccounts(new ArrayList<MemberAdvertisingAccount>());
		return m;
	}

	// private CompanyInfo prepareCompanyInfo() {
	// CompanyInfo info = new CompanyInfo();
	// info.setCity("Brno");
	// info.setCountry("CZ");
	// info.setEmail("Example@ex.com");
	// info.setPhone("+421605666666");
	// info.setStreet("Lidicka 61");
	// info.setZipCode(63800);
	//
	// return info;
	// }
}
