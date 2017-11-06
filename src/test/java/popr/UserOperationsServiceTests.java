package popr;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import popr.model.*;
import popr.repository.ProviderRepository;
import popr.repository.ServiceOrderRepository;
import popr.repository.ServiceRepository;
import popr.repository.ZoneRepository;
import popr.service.UserOperationsService;
import popr.service.UserService;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserOperationsServiceTests {

    @Autowired
    private UserOperationsService userOperationsService;

    @Autowired
    private ServiceOrderRepository serviceOrderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    private ServiceRepository serviceRepository;

    private Service service;
    private Provider provider;
    private Zone zone;
    private User user;

    @Before
    public void setUp() throws Exception {

        user = new User();

        zone = new Zone();
        zone.setPostalCode("01-111");

        provider = new Provider();
        provider.setZone(zone);

        service = new Service();
        service.setPrice(1);
        service.setEstimatedRealisationTime(1);
        service.setDescription("test");
        service.setProvider(provider);

        serviceRepository = mock(ServiceRepository.class);
        when(serviceRepository.findOne(any(Long.class))).thenReturn(service);

        serviceOrderRepository = mock(ServiceOrderRepository.class);
        when(serviceOrderRepository.save(any(ServiceOrder.class))).then(invocation -> invocation.getArguments()[0]);

        userService = mock(UserService.class);
        when(userService.readCurrent()).thenReturn(user);

        userOperationsService = new UserOperationsService(serviceOrderRepository, serviceRepository, userService, providerRepository, zoneRepository);
    }

    @Test
    public void canCreateServiceOrder() throws Exception {
        ServiceOrder order = userOperationsService.createServiceOrder("test", zone, 1L, 1L);
        assert order.getDescription().equals("test");
        assert order.getService().equals(service);
        assert order.getZone().equals(zone);
    }
}
