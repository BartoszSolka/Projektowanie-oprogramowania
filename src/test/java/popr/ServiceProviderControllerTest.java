package popr;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import popr.controller.ServiceProviderController;
import popr.model.Provider;
import popr.model.Zone;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ServiceProviderControllerTest {
	
	private int totalZones = 1;
	
	@Autowired
	private ServiceProviderController testedClass;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void setLocationTest() {
		Long providerId = 4L, zoneId = 1L;
		Provider provider = testedClass.setLocation(providerId, zoneId);
		assert provider.getZone().getId().equals(zoneId);
		assert provider.getId().equals(providerId);
		
		provider = testedClass.setLocation(null, zoneId);
		assertNull(provider);
		
		provider = testedClass.setLocation(providerId, null);
		assertNull(provider);
		
		provider = testedClass.setLocation(null, null);
		assertNull(provider);
		
		provider = testedClass.setLocation(-1L, 1L);
		assertNull(provider);
		
		provider = testedClass.setLocation(1L, -1L);
		assertNull(provider);
		
		provider = testedClass.setLocation(-1L, -1L);
		assertNull(provider);
	}
	
	@Test
	public void setStatusTest() {
		Long providerId = 4L;
		Provider provider = testedClass.setStatus(true, providerId);
		assert provider.getId().equals(4L);
		assert provider.isActive();
		
		provider = testedClass.setStatus(false, providerId);
		assert provider.getId().equals(4L);
		assert !provider.isActive();
	}
	
	@Test
	public void getZonesTest() {
		List<Zone> zones = testedClass.getZones();
		
		assertNotNull(zones);
		assertTrue(zones.size() == totalZones);
	}
	
	

}
