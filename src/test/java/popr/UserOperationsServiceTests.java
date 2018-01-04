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
import popr.model.*;
import popr.model.enums.ServiceOrderStatusDict;
import popr.repository.*;
import popr.service.MailServiceImpl;
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

    private MailServiceImpl mailServiceImpl;

    @InjectMocks
    private UserOperationsService userOperationsService;

    private Service service;
    private Zone zone;
    private ServiceOrder serviceOrder;
    private ServiceOrderStatus serviceOrderStatus;

    @Before
    public void setUp() throws Exception {
        service = new Service();
        service.setId(1L);

        zone = new Zone();
        zone.setPostalCode("01-111");

        serviceOrder = new ServiceOrder();
        serviceOrder.setId(1L);

        serviceOrderStatus = new ServiceOrderStatus();

        serviceRepository = mock(ServiceRepository.class);
        when(serviceRepository.findOne(anyLong())).thenReturn(service);
        when(serviceRepository.findById(anyLong())).thenReturn(service);


        serviceOrderStatusRepository = mock(ServiceOrderStatusRepository.class);
        when(serviceOrderStatusRepository.findByServiceOrderAndCurrentIsTrue(any(ServiceOrder.class))).thenReturn(serviceOrderStatus);
        when(serviceOrderStatusRepository.save(any(ServiceOrderStatus.class))).then(invocationOnMock -> invocationOnMock.getArguments()[0]);

        serviceOrderRepository = mock(ServiceOrderRepository.class);
        when(serviceOrderRepository.save(any(ServiceOrder.class))).then(invocationOnMock -> invocationOnMock.getArguments()[0]);
        when(serviceOrderRepository.findById(anyLong())).thenReturn(serviceOrder);

        zoneRepository = mock(ZoneRepository.class);
        when(zoneRepository.findByPostalCode(zone.getPostalCode())).thenReturn(zone);

        complaintRepository = mock(ComplaintRepository.class);
        when(complaintRepository.save(any(Complaint.class))).then(invocationOnMock -> invocationOnMock.getArguments()[0]);

        userService = mock(UserService.class);

        userOperationsService = new UserOperationsService(serviceOrderRepository, serviceOrderStatusRepository,
                serviceRepository, userService, zoneRepository, providerRepository, complaintRepository, mailServiceImpl);
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

    @Test
    public void canEditServiceOrder() throws Exception {
        ServiceOrder serviceOrder = new ServiceOrder();
        when(serviceOrderRepository.findById(anyLong())).thenReturn(serviceOrder);

        String description = "test";
        String address = "koszykowa 1";

        ServiceOrder editedOrder = userOperationsService.editServiceOrder(description, zone.getPostalCode(), service.getId(), serviceOrder.getId(), address);

        assert editedOrder.getService().equals(service);
        assert editedOrder.getZone().equals(zone);
        assert editedOrder.getAddress().equals(address);
        assert editedOrder.getDescription().equals(description);
    }

    @Test
    public void canRateServiceOrder() throws Exception {
        String description = "fajne";
        ServiceOrder ratedOrder = userOperationsService.rateServiceOrder(serviceOrder.getId(), 3, description);
        assert ratedOrder.getRating().equals(3);
        assert ratedOrder.getRatingDescription().equals(description);
    }

    @Test
    public void canCreateComplaint() throws Exception {
        String description = "niefajne";
        Complaint complaint = userOperationsService.createComplaint(description, serviceOrder.getId());
        assert complaint.getDescription().equals(description);
    }

    @Test
    public void canCancelServiceOrder() throws Exception {
        ServiceOrderStatus serviceOrderStatus = userOperationsService.cancelServiceOrder(serviceOrder.getId());

        assert serviceOrderStatus.getOrderStatusDict().equals(ServiceOrderStatusDict.CANCELED);
    }
}
