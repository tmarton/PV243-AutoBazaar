package cz.muni.fi.pv243.test;

import cz.muni.fi.pv243.dao.VehicleBrandDao;
import cz.muni.fi.pv243.model.VehicleBrand;
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
public class VehicleBrandDaoTest {
    
    @Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "test_vehicle_brand.war")
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
    private VehicleBrandDao dao;    
    
    public VehicleBrandDaoTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
        dao.removeAll();
    }
    
    @Test
    public void createValidVehicleBrand() {
        VehicleBrand v = prepareVehicleBrand();
        assertNull(v.getId());
        
        dao.save(v);
        assertNotNull(v.getId());
        
        VehicleBrand loaded = dao.getByID(v.getId());
        assertTrue(v.equals(loaded));
    }
    
    @Test
    public void createExistingVehicleBrand() {
        VehicleBrand v = prepareVehicleBrand();
        assertNull(v.getId());
        
        dao.save(v);
        try {
            dao.save(v);
            fail("saved already saved VehicleBrand");
        } catch (Exception e) {
            // ok
        }
        
        v = prepareVehicleBrand();
        try {
            dao.save(v);
            fail("saved VehicleBrand with non-unique name");
        } catch (Exception e) {
            // ok
        }        
    }    
    
    @Test
    public void createInvalidVehicleBrand() {
        try {
            dao.save(null);
            fail("saved null VehicleBrand");
        } catch (Exception e) {
            // ok
        } 
        
        VehicleBrand v = prepareVehicleBrand();
        v.setId(1l);
        
        try {
            dao.save(v);
            fail("saved VehicleBrand with id");
        } catch (Exception e) {
            // ok
        }
        
        v = new VehicleBrand();
        try {
            dao.save(v);
            fail("saved VehicleBrand with null name");
        } catch (Exception e) {
            // ok
        }    
        
        v.setName("");
        try {
            dao.save(v);
            fail("saved VehicleBrand with empty name");
        } catch (Exception e) {
            // ok
        }         
    }     
    
    @Test
    public void getNullVehicleBrand() {
        try {
            dao.getByID(null);
            fail("returned VehicleBrand when searching for null");
        } catch (Exception e) {
            // ok
        }
    }    

    @Test
    public void updateVehicleBrand(){
        try{
            dao.update(null);
            fail("update with null");
        }catch(Exception ex){
            //ok
        }

        VehicleBrand v = new VehicleBrand();
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

        VehicleBrand found = dao.getByID(v.getId());
        assertTrue(v.equals(found));
        
        v.setName(null);
        try {
            dao.update(v);
            fail("updated VehicleBrand with null name");
        } catch (Exception e) {
            // ok
        } 
        
        v.setName("");
        try {
            dao.update(v);
            fail("updated VehicleBrand with empty name");
        } catch (Exception e) {
            // ok
        }         
    }
    
    @Test
    public void deleteVehicleBrand(){
        try{
            dao.remove(null);
            fail();
        }catch(Exception ex){
            // ok
        }
        
        VehicleBrand v = prepareVehicleBrand();
        
        dao.save(v);
        
        assertEquals(v, dao.getByID(v.getId()));

        dao.remove(v);

        assertNull(dao.getByID(v.getId()));
    }

    @Test
    public void getAllVehicleBrands() {
        assertTrue(dao.getAll().isEmpty());

        VehicleBrand m1 = new VehicleBrand();
        m1.setName("brand1");
        VehicleBrand m2 = new VehicleBrand();
        m2.setName("brand2");        

        dao.save(m1);
        dao.save(m2);

        List<VehicleBrand> expected = Arrays.asList(m1, m2);

        List<VehicleBrand> actual = dao.getAll();

        assertArrayEquals(expected.toArray(), actual.toArray());
    }    
    
    private VehicleBrand prepareVehicleBrand() {
        VehicleBrand v = new VehicleBrand();
        v.setName("Škoda");
        return v;
    }
    
}
