package cz.muni.fi.pv243.services;

import cz.muni.fi.pv243.dao.VehicleModelDao;
import cz.muni.fi.pv243.dto.VehicleBrandDto;
import cz.muni.fi.pv243.dto.VehicleModelDto;
import cz.muni.fi.pv243.model.VehicleBrand;
import cz.muni.fi.pv243.model.VehicleModel;
import cz.muni.fi.pv243.services.impl.VehicleModelServiceImpl;
import org.dozer.DozerBeanMapper;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Johny
 */
@RunWith(Arquillian.class)
public class VehicleModelServiceTest {
    
    @Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "vehicle_model_service_test.war")
            .addPackage("cz.muni.fi.pv243.model")
            .addPackage("cz.muni.fi.pv243.services")
            .addPackage("cz.muni.fi.pv243.services.impl")
            .addPackage("cz.muni.fi.pv243.enums")
            .addPackage("cz.muni.fi.pv243.dao")
            .addPackage("cz.muni.fi.pv243.dao.impl")
            .addPackage("cz.muni.fi.pv243.dto")
            .addPackage("cz.muni.fi.pv243.util")
            .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml").resolve("net.sf.dozer:dozer").withTransitivity().asFile())
            .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml").resolve("org.mockito:mockito-all").withTransitivity().asFile())
            .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml").resolve("org.jadira.usertype:usertype.core").withTransitivity().asFile())
            .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml").resolve("joda-time:joda-time").withTransitivity().asFile())                
            .addAsResource("META-INF/persistence.xml", ArchivePaths.create("META-INF/persistence.xml"))
            .addAsResource("dozerBeanMapping.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }   
    
    public VehicleModelServiceTest() {
    }
    
    //@Inject // can't set dao into proxy :/
    private VehicleModelService service;
    private VehicleModelDao dao;
    private DozerBeanMapper mapper;
    
    @Before
    public void setUp() {
        service = new VehicleModelServiceImpl();
        dao = mock(VehicleModelDao.class);
        service.setDao(dao);
        if (!dao.equals(service.getDao()))
            fail("Can't set mocked instance into service: " + dao.toString()  + "; found " + service.getDao().toString() + " instead");
        mapper = new DozerBeanMapper();
    }

    /**
     * Test of getByID method, of class VehicleModelServiceImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetByID() throws Exception {
        Long id = 1L;
        service.getByID(id);
        verify(dao).getByID(id);        
    }

    /**
     * Test of getAll method, of class VehicleModelServiceImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAll() throws Exception {
        service.getAll();
        verify(dao).getAll();         
    }

    /**
     * Test of save method, of class VehicleModelServiceImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testSave() throws Exception {
        VehicleModelDto dto = new VehicleModelDto();
        service.save(dto);
        verify(dao).save(mapper.map(dto, VehicleModel.class));        
    }

    /**
     * Test of update method, of class VehicleModelServiceImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdate() throws Exception {
        VehicleModelDto dto = new VehicleModelDto();
        service.update(dto);
        verify(dao).update(mapper.map(dto, VehicleModel.class));            
    }

    /**
     * Test of remove method, of class VehicleModelServiceImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testRemove() throws Exception {
        VehicleModelDto dto = new VehicleModelDto();
        service.remove(dto);
        verify(dao).remove(mapper.map(dto, VehicleModel.class));         
    }
    
    /**
     * Test of getAllVehicleModelsByBrand method, of class VehicleModelServiceImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllVehicleModelsByBrand() throws Exception {
        VehicleBrandDto brand = new VehicleBrandDto();
        service.getAllVehicleModelsByBrand(brand);
        verify(dao).getAllVehicleModelsByBrand(mapper.map(brand, VehicleBrand.class));         
    }
}
