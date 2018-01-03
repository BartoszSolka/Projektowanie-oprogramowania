package popr;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import popr.model.Service;
import popr.model.ServiceOrder;
import popr.model.ServiceOrderStatus;
import popr.model.Zone;
import popr.repository.*;
import popr.service.UserOperationsService;
import popr.service.UserService;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserOperationsServiceTests {

    private ServiceRepository serviceRepository;

    private ZoneRepository zoneRepository;

    private ServiceOrderRepository serviceOrderRepository;

    private ServiceOrderStatusRepository serviceOrderStatusRepository;

    private UserService userService;

    private ProviderRepository providerRepository;

    private ComplaintRepository complaintRepository;

    @InjectMocks
    private UserOperationsService userOperationsService;

    private Service service;
    private Zone zone;

    @Before
    public void setUp() throws Exception {
        service = new Service();
        service.setId(1L);

        zone = new Zone();
        zone.setPostalCode("01-111");

        serviceRepository = mock(ServiceRepository.class);
        when(serviceRepository.findOne(anyLong())).thenReturn(service);

        serviceOrderStatusRepository = mock(ServiceOrderStatusRepository.class);
        when(serviceOrderStatusRepository.save(any(ServiceOrderStatus.class))).then(invocationOnMock -> invocationOnMock.getArguments()[0]);

        serviceOrderRepository = mock(ServiceOrderRepository.class);
        when(serviceOrderRepository.save(any(ServiceOrder.class))).then(invocationOnMock -> invocationOnMock.getArguments()[0]);

        zoneRepository = mock(ZoneRepository.class);
        when(zoneRepository.findByPostalCode(zone.getPostalCode())).thenReturn(zone);

        userService = mock(UserService.class);

        userOperationsService = new UserOperationsService(serviceOrderRepository, serviceOrderStatusRepository,
                serviceRepository, userService, zoneRepository, providerRepository, complaintRepository);
    }

    @Test
    public void canCreateServiceOrder() throws Exception {
        String description = "test";
        String address = "koszykowa 1";
        Long serviceId = 1L;

        ServiceOrder serviceOrder = userOperationsService.createServiceOrder(description, address, zone, serviceId);

        assert serviceOrder.getDescription().equals(description);
        assert serviceOrder.getAddress().equals(address);
        assert serviceOrder.getZone().equals(zone);
        assert serviceOrder.getService().getId().equals(serviceId);
    }
}
