package cz.muni.fi.pv243.test;

import cz.muni.fi.pv243.dao.AdvertisementDao;
import cz.muni.fi.pv243.dao.VehicleBrandDao;
import cz.muni.fi.pv243.dao.VehicleModelDao;
import cz.muni.fi.pv243.dao.VehiclePhotoDao;
import cz.muni.fi.pv243.enums.VehicleBodyType;
import cz.muni.fi.pv243.model.Advertisement;
import cz.muni.fi.pv243.model.VehicleBrand;
import cz.muni.fi.pv243.model.VehicleModel;
import cz.muni.fi.pv243.model.VehiclePhoto;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 *
 * @author Johny
 */
@RunWith(Arquillian.class)
public class VehiclePhotoDaoTest {

    @Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "test_vehicle_photo.war")
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
    private VehiclePhotoDao dao;
    @Inject
    private AdvertisementDao advertDao;  
    @Inject
    private VehicleModelDao modelDao; 
    @Inject
    private VehicleBrandDao brandDao;     

    public VehiclePhotoDaoTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        dao.removeAll();
    }

    @Test
    public void createValidVehiclePhoto() {
        VehiclePhoto v = prepareVehiclePhoto();
        assertNull(v.getId());

        dao.save(v);
        assertNotNull(v.getId());

        VehiclePhoto loaded = dao.getByID(v.getId());
        assertTrue(v.equals(loaded));
    }

    @Test
    public void createExistingVehiclePhoto() {
        VehiclePhoto v = prepareVehiclePhoto();
        assertNull(v.getId());

        dao.save(v);
        try {
            dao.save(v);
            fail("saved already saved VehiclePhoto");
        } catch (Exception e) {
            // ok
        }
    }

    @Test
    public void createInvalidVehiclePhoto() {
        try {
            dao.save(null);
            fail("saved null VehiclePhoto");
        } catch (Exception e) {
            // ok
        }

        VehiclePhoto v = prepareVehiclePhoto();
        v.setId(1l);

        try {
            dao.save(v);
            fail("saved VehiclePhoto with id");
        } catch (Exception e) {
            // ok
        }

        v = new VehiclePhoto();
        try {
            dao.save(v);
            fail("saved VehiclePhoto with null url");
        } catch (Exception e) {
            // ok
        }

        v = new VehiclePhoto();
        try {
            v.setUrl(new URL("http://www.example.com/docs/resource1.jp"));
        } catch (MalformedURLException ex) {
            fail("malformed url in test");
        }
        try {
            dao.save(v);
            fail("saved VehiclePhoto with non-image url");
        } catch (Exception e) {
            // ok
        }
    }

    @Test
    public void getNullVehiclePhoto() {
        try {
            dao.getByID(null);
            fail("returned VehiclePhoto when searching for null");
        } catch (Exception e) {
            // ok
        }
    }

    @Test
    public void updateVehiclePhoto() {
        try {
            dao.update(null);
            fail("update with null");
        } catch (Exception ex) {
            //ok
        }

        VehiclePhoto v = new VehiclePhoto();
        try {
            dao.update(v);
            fail("update non consistent entity");
        } catch (Exception ex) {
            //ok
        }

        v = prepareVehiclePhoto();
        dao.save(v);

        v.setComment("comment text 123");
        dao.update(v);

        VehiclePhoto found = dao.getByID(v.getId());
        assertTrue(v.equals(found));

        v.setUrl(null);
        try {
            dao.update(v);
            fail("updated VehiclePhoto with null url");
        } catch (Exception e) {
            // ok
        }

        try {
            v.setUrl(new URL("http://www.example.com/docs/resource1.jp"));
        } catch (MalformedURLException ex) {
            fail("malformed url in test");
        }
        try {
            dao.update(v);
            fail("updated VehiclePhoto with non-image url");
        } catch (Exception e) {
            // ok
        }
    }

    @Test
    public void deleteVehiclePhoto() {
        try {
            dao.remove(null);
            fail();
        } catch (Exception ex) {
            // ok
        }

        VehiclePhoto v = prepareVehiclePhoto();

        dao.save(v);

        assertEquals(v, dao.getByID(v.getId()));

        dao.remove(v);

        assertNull(dao.getByID(v.getId()));
    }

    @Test
    public void getAllVehiclePhotos() {
        assertTrue(dao.getAll().isEmpty());

        VehiclePhoto p1 = new VehiclePhoto();
        VehiclePhoto p2 = new VehiclePhoto();
        p1.setComment("comment 1");
        p2.setComment("comment 2");
        try {
            p1.setUrl(new URL("http://first.url.dev/image.jpg"));
            p2.setUrl(new URL("http://second.url.dev/img2.png"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(VehiclePhotoDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        dao.save(p1);
        dao.save(p2);

        List<VehiclePhoto> expected = Arrays.asList(p1, p2);

        List<VehiclePhoto> actual = dao.getAll();

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void testGetAllVehiclePhotosByAdvertisement() {

        try {
            dao.getAllVehiclePhotosByAdvertisement(null);
            fail("getting all vehicle photos by null advert");
        } catch (Exception ex) {
            // ok
        }

        Advertisement advert = prepareAdvertisement();

        try{
            dao.getAllVehiclePhotosByAdvertisement(advert);
            fail("getting all vehicle photos by non-existing advert");
        }catch(Exception ex){
            // ok
        }           
        advertDao.save(advert);

        assertTrue(dao.getAllVehiclePhotosByAdvertisement(advert).isEmpty());

        VehiclePhoto p = prepareVehiclePhoto();
        p.setComment("comment 1");
        dao.save(p);

        VehiclePhoto p2 = prepareVehiclePhoto();
        p2.setComment("comment 2");
        dao.save(p2);  
        
        advert.addVehiclePhoto(p);

        List<VehiclePhoto> res = dao.getAllVehiclePhotosByAdvertisement(advert);
        assertTrue(res.size() == 1);

        assertEquals(p, res.get(0));

        assertEquals(p.getAdvertisement(), res.get(0).getAdvertisement());
    }

    private VehiclePhoto prepareVehiclePhoto() {
        VehiclePhoto v = new VehiclePhoto();
        try {
            v.setUrl(new URL("http://www.example.com/docs/resource1.gif"));
        } catch (MalformedURLException ex) {
            fail("MalformedURLException");
        }
        return v;
    }

    private Advertisement prepareAdvertisement() {
        VehicleBrand brand = new VehicleBrand();
        brand.setName("brand");
        brandDao.save(brand);
        
        VehicleModel model = new VehicleModel();
        model.setName("model");
        modelDao.save(model);
        
        Advertisement a = new Advertisement();
        a.setBodyType(VehicleBodyType.Coupe);
        a.setEngineDisplacement(5000);
        a.setBrand(brand);
        a.setModel(model);
        a.setCreationDate(DateTime.now());
        a.setProductionDate(DateTime.now());
        a.setDescription("desc");
        
        return a;
    }    
}
