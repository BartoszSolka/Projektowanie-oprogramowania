package popr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import popr.interfaces.ProviderOperationsInterface;
import popr.interfaces.UserOperationsInterface;
import popr.model.*;
import popr.repository.*;
import popr.service.MailService;
import popr.service.MailServiceImpl;
import popr.service.UserService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class ServiceUserController implements ServiceUserManager {

    private final ZoneRepository zoneRepository;
    private final ServiceOrderRepository serviceOrderRepository;
    private final ServiceOrderStatusRepository serviceOrderStatusRepository;
    private final UserService userService;
    private final ComplaintRepository complaintRepository;
    private final MailServiceImpl mailService;

    @Autowired
    private UserOperationsInterface userOperationsService;

    @Override
    @PostMapping(path = "/addOrder")
    public ServiceOrder createServiceOrder(String description, String address, String postalCode, Long serviceID) {
        Zone zone = zoneRepository.findByPostalCode(postalCode);
        return userOperationsService.createServiceOrder(description, address,  zone, serviceID );
    }

    @Override
    @GetMapping(path = "/service", produces = APPLICATION_JSON_VALUE)
    public Page<ServiceType> getServices(Pageable pageable) {
        return userOperationsService.getServices(pageable);
    }

    @Override
    @GetMapping(path = "/zoneService", produces = APPLICATION_JSON_VALUE)
    public Page<ServiceOrder> getServiceOrdersByZone(String postalCode, Pageable pageable) {
        Zone zone = zoneRepository.findByPostalCode(postalCode);
        Page<ServiceOrder> serviceOrders = userOperationsService.getServiceOrdersByZone(zone, pageable);
        for (ServiceOrder serviceOrder: serviceOrders
                ) {
            ServiceOrderStatus serviceOrderStatus = userOperationsService.getServiceOrderStatus(serviceOrder);
            serviceOrder.setStatusDict((serviceOrderStatus.getOrderStatusDict()).toString());
            serviceOrder.setComment((serviceOrderStatus.getComment()));
        }
        return serviceOrders;
    }

    @Override
    @GetMapping(path = "/zones", produces = APPLICATION_JSON_VALUE)
    public Page<Zone> getZones(Pageable pageable) {
        return zoneRepository.findAll(pageable);
    }

    @Override
    @GetMapping(path = "/userService", produces = APPLICATION_JSON_VALUE)
    public Page<ServiceOrder> getServiceOrdersByUser(Pageable pageable) {
        Person user = userService.readCurrent();
        Page<ServiceOrder> serviceOrders = userOperationsService.getServiceOrdersByUser(pageable);
        for (ServiceOrder serviceOrder: serviceOrders
                ) {
            ServiceOrderStatus serviceOrderStatus = userOperationsService.getServiceOrderStatus(serviceOrder);
            serviceOrder.setStatusDict((serviceOrderStatus.getOrderStatusDict()).toString());
            serviceOrder.setComment((serviceOrderStatus.getComment()));
        }
        return serviceOrders;
    }

    @Override
    @GetMapping(path = "/orderStatus", produces = APPLICATION_JSON_VALUE)
    public ServiceOrderStatus getServiceOrderStatus(Long orderId) {
        ServiceOrder serviceOrder = serviceOrderRepository.findById(orderId);
        return userOperationsService.getServiceOrderStatus(serviceOrder);
    }

    @Override
    @PostMapping(path = "/cancelOrder")
    public ServiceOrderStatus cancelServiceOrder(Long orderId) {
        return userOperationsService.cancelServiceOrder(orderId);
    }

    @Override
    @PostMapping(path = "/editOrder")
    public ServiceOrder editServiceOrder(String description, String postalCode, Long serviceId, Long orderId, String address) {
        return userOperationsService.editServiceOrder(description, postalCode, serviceId, orderId, address);
    }

    @Override
    @PostMapping(path = "/rateOrder")
    public ServiceOrder rateServiceOrder(Long orderId, Integer rating, String description) {
        return userOperationsService.rateServiceOrder(orderId, rating, description);
    }

    @Override
    @PostMapping(path = "/complaint")
    public ServiceOrder createComplaint(String complaint, Long orderId) {
        return userOperationsService.createComplaint(complaint, orderId);
    }


}
