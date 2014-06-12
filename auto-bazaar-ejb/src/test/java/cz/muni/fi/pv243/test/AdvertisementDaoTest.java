package cz.muni.fi.pv243.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
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
import org.joda.time.DateTime;
import org.joda.time.JodaTimePermission;
import org.joda.time.format.DateTimeFormat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import cz.muni.fi.pv243.dao.AdvertisementDao;
import cz.muni.fi.pv243.dao.VehicleBrandDao;
import cz.muni.fi.pv243.dao.VehicleModelDao;
import cz.muni.fi.pv243.enums.FuelType;
import cz.muni.fi.pv243.enums.VehicleBodyType;
import cz.muni.fi.pv243.model.Advertisement;
import cz.muni.fi.pv243.model.VehicleBrand;
import cz.muni.fi.pv243.model.VehicleModel;

@RunWith(Arquillian.class)
public class AdvertisementDaoTest {

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(WebArchive.class, "test_advertisement.war")
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
	private AdvertisementDao adsDao;

	@Inject
	private VehicleBrandDao brandsDao;

	@Inject
	private VehicleModelDao modelDao;

	private VehicleBrand brand;
	private VehicleModel modelOfBrand;

	public AdvertisementDaoTest() {

	}

	@Before
	public void setUp() {

		this.brand = new VehicleBrand();
		this.brand.setName("VW");
		brandsDao.save(this.brand);

		this.modelOfBrand = new VehicleModel();
		this.modelOfBrand.setBrand(this.brand);
		this.modelOfBrand.setName("Golf");
		modelDao.save(this.modelOfBrand);
	}

	@After
	public void tearDown() {
		adsDao.removeAll();
		modelDao.removeAll();
		brandsDao.removeAll();
	}

	@Test
	public void createAdvertisement() {
		Advertisement ad = prepareValidAd();
		assertNull(ad.getId());

		adsDao.save(ad);
		assertNotNull(ad.getId());

		Advertisement loaded = adsDao.getByID(ad.getId());
		assertTrue(deepEqual(ad, loaded));
	}

	@Test
	public void createExistingAdvertisement() {
		Advertisement ad = prepareValidAd();
		assertNull(ad.getId());

		adsDao.save(ad);

		try {
			adsDao.save(ad);
			fail("save already saved ad");
		} catch (Exception ex) {
			// ok
		}
	}

	//
	@Test
	public void createInvalidAdvertisement() {
		try {
			adsDao.save(null);
			fail("saved null ad");
		} catch (Exception e) {
			// ok
		}

		Advertisement ad = prepareValidAd();
		ad.setId(1l);

		try {
			adsDao.save(ad);
			fail("saved ad with id");
		} catch (Exception e) {
			// ok
		}

		ad = prepareValidAd();
		ad.setDescription(null);
		try {
			adsDao.save(ad);
			fail("saved ad with null description");
		} catch (Exception e) {
			// ok
		}

		ad = prepareValidAd();
		ad.setBrand(null);
		try {
			adsDao.save(ad);
			fail("saved ad with null brand");
		} catch (Exception e) {
			// ok
		}

		ad = prepareValidAd();
		ad.setModel(null);
		try {
			adsDao.save(ad);
			fail("saved ad with null model");
		} catch (Exception e) {
			// ok
		}

		// etc

	}

	@Test
	public void getNullAdvertisement() {
		try {
			adsDao.getByID(null);
			fail("returned ad when searching for null");
		} catch (Exception e) {
			// ok
		}
	}

	
	@Test
	public void updateAdvertisement() {
		try {
			adsDao.update(null);
			fail("update with null");
		} catch (Exception ex) {
			// ok
		}

		Advertisement ad = new Advertisement();
		try {
			adsDao.update(ad);
			fail("update non consistent entity");
		} catch (Exception ex) {
			// ok
		}

		ad = prepareValidAd();
		adsDao.save(ad);

		ad.setDescription("new updated description");
		adsDao.update(ad);

		Advertisement loaded = adsDao.getByID(ad.getId());
		assertTrue(deepEqual(ad, loaded));

		ad.setDescription(null);
		try {
			adsDao.update(ad);
			fail("updated ad with null description");
		} catch (Exception e) {
			// ok
		}
	}

	@Test
	public void deleteAdvertisement() {
		try {
			adsDao.remove(null);
			fail("removed null ad");
		} catch (Exception ex) {
			// ok
		}

		Advertisement ad = prepareValidAd();

		adsDao.save(ad);

		assertTrue(deepEqual(ad, adsDao.getByID(ad.getId())));

		adsDao.remove(ad);

		assertNull(adsDao.getByID(ad.getId()));
	}

	@Test
	public void getAllAdvertisements() {
		assertTrue(adsDao.getAll().isEmpty());

		Advertisement ad1 = prepareValidAd();
		ad1.setDescription("first maserati");

		Advertisement ad2 = prepareValidAd();
		ad2.setDescription("second maserati");

		adsDao.save(ad1);
		adsDao.save(ad2);

		List<Advertisement> expected = Arrays.asList(ad1, ad2);

		List<Advertisement> actual = adsDao.getAll();

		assertEquals(expected.size(), actual.size());

		for (int i = 0; i < expected.size(); i++) {
			assertTrue(deepEqual(expected.get(i), actual.get(i)));
		}
	}

	private Advertisement prepareValidAd() {

		Advertisement ad = new Advertisement();
		ad.setBodyType(VehicleBodyType.Coupe);
		ad.setBrand(this.brand);
		ad.setModel(this.modelOfBrand);

		ad.setCreationDate(DateTimeFormat.forPattern("dd/MM/yyyy")
				.parseDateTime("04/02/2014"));
		ad.setDescription("Some short description");
		ad.setEngineDisplacement(3998);
		ad.setFuelType(FuelType.Gasoline);
		ad.setProductionDate(DateTimeFormat.forPattern("dd/MM/yyyy")
				.parseDateTime("04/02/2011"));

		return ad;

	}

	private boolean deepEqual(Advertisement ad1, Advertisement ad2) {

		if (!ad1.getId().equals(ad2.getId())) {
			return false;
		}
		if (ad1.getBodyType() != ad2.getBodyType()) {
			return false;
		}
		if (ad1.getBrand().getId() != ad2.getBrand().getId()) {
			return false;
		}
		if (ad1.getModel().getId() != ad2.getModel().getId()) {
			return false;
		}
		if (!ad1.getProductionDate().equals(ad2.getProductionDate())) {
			return false;
		}
		if (ad1.getFuelType() != ad2.getFuelType()) {
			return false;
		}
		if (!ad1.getCreationDate().equals(ad2.getCreationDate())) {
			return false;
		}
		if (!ad1.getDescription().equals(ad2.getDescription())) {
			return false;
		}
		if (ad1.getEngineDisplacement() != ad2.getEngineDisplacement()) {
			return false;
		}
		return true;

	}
}
