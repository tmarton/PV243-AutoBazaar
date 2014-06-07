package cz.muni.fi.pv243.test;

import cz.muni.fi.pv243.dao.VehicleBrandDao;
import cz.muni.fi.pv243.dao.VehicleModelDao;
import cz.muni.fi.pv243.model.VehicleBrand;
import cz.muni.fi.pv243.model.VehicleModel;
import java.util.*;
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
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 *
 * @author Johny
 */
@RunWith(Arquillian.class)
public class VehicleModelDaoTest {
    
    @Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "test_vehicle_model.war")
            .addPackage("cz.muni.fi.pv243.model")
            .addPackage("cz.muni.fi.pv243.enums")
            .addPackage("cz.muni.fi.pv243.dao")
            .addPackage("cz.muni.fi.pv243.util")
            .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml").resolve("org.jadira.usertype:usertype.core").withTransitivity().asFile())
            .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml").resolve("joda-time:joda-time").withTransitivity().asFile())                
            .addAsResource("META-INF/persistence.xml", ArchivePaths.create("META-INF/persistence.xml"))
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }    
    
    @Inject
    private VehicleModelDao dao;
    @Inject
    private VehicleBrandDao brandDao;    
    
    public VehicleModelDaoTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
        dao.removeAll();
    }
    
    @Test
    public void createValidVehicleModel() {
        VehicleModel v = prepareVehicleModel();
        assertNull(v.getId());
        
        dao.save(v);
        assertNotNull(v.getId());
        
        VehicleModel loaded = dao.getByID(v.getId());
        assertTrue(v.equals(loaded));
    }
    
    @Test
    public void createExistingVehicleModel() {
        VehicleModel v = prepareVehicleModel();
        assertNull(v.getId());
        
        dao.save(v);
        try {
            dao.save(v);
            fail("saved already saved VehicleModel");
        } catch (Exception e) {
            // ok
        }
        
        v = prepareVehicleModel();
        try {
            dao.save(v);
            fail("saved VehicleModel with non-unique name");
        } catch (Exception e) {
            // ok
        }        
    }    
    
    @Test
    public void createInvalidVehicleModel() {
        try {
            dao.save(null);
            fail("saved null VehicleModel");
        } catch (Exception e) {
            // ok
        } 
        
        VehicleModel v = prepareVehicleModel();
        v.setId(1l);
        
        try {
            dao.save(v);
            fail("saved VehicleModel with id");
        } catch (Exception e) {
            // ok
        }
        
        v = new VehicleModel();
        try {
            dao.save(v);
            fail("saved VehicleModel with null name");
        } catch (Exception e) {
            // ok
        }    
        
        v.setName("");
        try {
            dao.save(v);
            fail("saved VehicleModel with empty name");
        } catch (Exception e) {
            // ok
        }         
    }     
    
    @Test
    public void getNullVehicleModel() {
        try {
            dao.getByID(null);
            fail("returned VehicleModel when searching for null");
        } catch (Exception e) {
            // ok
        }
    }    
    

    @Test
    public void updateVehicleModel(){
        try{
            dao.update(null);
            fail("update with null");
        }catch(Exception ex){
            //ok
        }

        VehicleModel v = new VehicleModel();
        try{
            dao.update(v);
            fail("update non consistent entity");
        }catch(Exception ex){
            //ok
        }
        
        v.setName("abc");
        dao.save(v);

        v.setName("xyz");
        dao.update(v);

        VehicleModel found = dao.getByID(v.getId());
        assertTrue(v.equals(found));
        
        v.setName(null);
        try {
            dao.update(v);
            fail("updated VehicleModel with null name");
        } catch (Exception e) {
            // ok
        } 
        
        v.setName("");
        try {
            dao.update(v);
            fail("updated VehicleModel with empty name");
        } catch (Exception e) {
            // ok
        }         
    }

    @Test
    public void deleteVehicleModel(){
        try{
            dao.remove(null);
            fail();
        }catch(Exception ex){
            // ok
        }
        
        VehicleModel v = prepareVehicleModel();
        
        dao.save(v);
        
        assertEquals(v, dao.getByID(v.getId()));

        dao.remove(v);

        assertNull(dao.getByID(v.getId()));
    }

    @Test
    public void getAllVehicleModels() {
        assertTrue(dao.getAll().isEmpty());

        VehicleModel m1 = new VehicleModel();
        m1.setName("x");
        VehicleModel m2 = new VehicleModel();
        m2.setName("y");        

        dao.save(m1);
        dao.save(m2);

        List<VehicleModel> expected = Arrays.asList(m1, m2);

        List<VehicleModel> actual = dao.getAll();

        assertArrayEquals(expected.toArray(), actual.toArray());
    }    
    

    @Test
    public void testGetAllVehicleModelsByBrand() {
        
        try{
            dao.getAllVehicleModelsByBrand(null);
            fail("getting all vehicle models by null brand");
        }catch(Exception ex){
            // ok
        }         
        
        VehicleBrand brand = new VehicleBrand();
        brand.setName("Škoda");
        try{
            dao.getAllVehicleModelsByBrand(brand);
            fail("getting all vehicle models by non-existing brand");
        }catch(Exception ex){
            // ok
        }           
        brandDao.save(brand);
        
        assertTrue(dao.getAllVehicleModelsByBrand(brand).isEmpty());
        
        VehicleModel m = prepareVehicleModel();
        m.setBrand(brand);
        dao.save(m);
        
        VehicleModel m2 = new VehicleModel();
        m2.setName("Superb");
        dao.save(m2);        
        
        List<VehicleModel> res = dao.getAllVehicleModelsByBrand(brand);
        assertTrue(res.size() == 1);
        
        assertEquals(m, res.get(0));
        
        assertEquals(m.getBrand(), res.get(0).getBrand());
    }
    
    private VehicleModel prepareVehicleModel() {
        VehicleModel v = new VehicleModel();
        v.setName("Rapid");
        return v;
    }
    
}
