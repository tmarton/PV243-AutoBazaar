package cz.muni.fi.pv243.test;

import java.util.*;

import javax.inject.Inject;
import javax.naming.NamingException;

import cz.muni.fi.pv243.dao.AdvertisementDao;
import cz.muni.fi.pv243.dao.AdvertisingAccountDao;
import cz.muni.fi.pv243.dao.CompanyInfoDao;
import cz.muni.fi.pv243.dao.MemberDao;
import cz.muni.fi.pv243.model.AdvertisingAccount;
import cz.muni.fi.pv243.model.CompanyInfo;
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
public class AdvertisingAccountDaoTest {

	@Deployment
	public static Archive<?> createTestArchive() {

		return ShrinkWrap
				.create(WebArchive.class, "test_advertising_account.war")
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

	@Inject
	private MemberDao memberDao;

	@Inject
	private AdvertisingAccountDao adAccountDao;

	@Inject
	private CompanyInfoDao companyInfoDao;

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {
		memberDao.removeAll();
		adAccountDao.removeAll();
	}

	@Test
	public void createSimpleAdvertisingAccount() {
		assertTrue(adAccountDao.getAll().isEmpty());

		AdvertisingAccount acc = new AdvertisingAccount();
		acc.setActive(true);

		assertNull(acc.getId());

		adAccountDao.save(acc);

		assertNotNull(acc.getId());
		assertEquals(adAccountDao.getAll().size(), 1);
		assertAdvertisingAccountDeepEquals(acc,
				adAccountDao.getByID(acc.getId()));
	}

	@Test
	public void deleteSimpleAdvertisingAccount() {
		assertTrue(adAccountDao.getAll().isEmpty());

		AdvertisingAccount acc = new AdvertisingAccount();
		acc.setActive(true);

		assertNull(acc.getId());

		adAccountDao.save(acc);

		assertNotNull(acc.getId());
		
		adAccountDao.remove(acc);
		assertTrue(adAccountDao.getAll().isEmpty());
	}

	private AdvertisingAccount prepareAccount(Member member, CompanyInfo info) {
		AdvertisingAccount account = new AdvertisingAccount();
		account.setActive(true);
		account.setCompanyInfo(info);

		MemberAdvertisingAccount mToA = new MemberAdvertisingAccount();

		mToA.setAdvertisingAccount(account);
		mToA.setMember(member);
		account.setConnectedMembers(Arrays.asList(mToA));

		return account;

	}

	private CompanyInfo prepareCompanyInfo() {
		CompanyInfo info = new CompanyInfo();
		info.setCity("Brno");
		info.setCountry("CZ");
		info.setEmail("Example@ex.com");
		info.setPhone("+421605666666");
		info.setStreet("Lidicka 61");
		info.setZipCode(63800);

		return info;
	}

	private static Member getPreparedMember() {
		Member m = new Member();
		m.setName("test");
		m.setEmail("test@test.cz");
		m.setPhoneNumber("+421605666666");
		m.setAdvertisingAccounts(new ArrayList<MemberAdvertisingAccount>());
		return m;
	}

	private static void assertMemberDeepEquals(Member m1, Member m2) {
		assertEquals(m1.getId(), m2.getId());
		assertEquals(m1.getName(), m2.getName());
		assertEquals(m1.getEmail(), m2.getEmail());
		assertEquals(m1.getPhoneNumber(), m2.getPhoneNumber());

	}

	private static void assertAdvertisingAccountDeepEquals(
			AdvertisingAccount a1, AdvertisingAccount a2) {
		assertEquals(a1.getIsActive(), a2.getIsActive());
		assertCompanyInfoDeepEquals(a1.getCompanyInfo(), a2.getCompanyInfo());
		assertMemberAdvertisingAccountDeepEquals(a1.getConnectedMembers(),
				a2.getConnectedMembers());

	}

	private static void assertCompanyInfoDeepEquals(CompanyInfo info,
			CompanyInfo info2) {
		if (info == null && info2 == null) {
			return;
		}
		if (info == null || info2 == null) {
			throw new AssertionError("One of the infos is null");
		}

		assertEquals(info.getId(), info2.getId());
		assertEquals(info.getCity(), info2.getCity());
		assertEquals(info.getCountry(), info2.getCountry());
		assertEquals(info.getEmail(), info2.getEmail());
		assertEquals(info.getPhone(), info2.getPhone());
		assertEquals(info.getStreet(), info2.getStreet());
		assertEquals(info.getZipCode(), info2.getZipCode());
	}

	static void assertMemberCollectionDeepEquals(List<Member> expected,
			List<Member> actual) {

		assertEquals(expected.size(), actual.size());
		Collections.sort(expected, MemberKeyComparator);
		Collections.sort(actual, MemberKeyComparator);
		for (int i = 0; i < expected.size(); i++) {
			assertMemberDeepEquals(expected.get(i), actual.get(i));
		}
	}

	private static void assertMemberAdvertisingAccountDeepEquals(
			List<MemberAdvertisingAccount> l1, List<MemberAdvertisingAccount> l2) {
		assertEquals(l1.size(), l2.size());
		Collections.sort(l1, MemberAdvertisingAccountComparator);
		Collections.sort(l2, MemberAdvertisingAccountComparator);
		for (int i = 0; i < l2.size(); i++) {
			// should be replaced with assert deep copy
			assertEquals(l1.get(i).getId(), l2.get(i).getId());
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
		public int compare(MemberAdvertisingAccount o1,
				MemberAdvertisingAccount o2) {
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
