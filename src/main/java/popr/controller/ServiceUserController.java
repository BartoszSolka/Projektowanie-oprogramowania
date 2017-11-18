package popr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import popr.interfaces.ProviderOperationsInterface;
import popr.interfaces.UserOperationsInterface;
import popr.model.*;
import popr.repository.ProviderRepository;
import popr.repository.ZoneRepository;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class ServiceUserController implements ServiceUserManager {
    @Autowired
    private UserOperationsInterface userOperationsService;

    @Override
    @PostMapping(path = "/addOrder")
    public ServiceOrder createServiceOrder(String description, Zone zone, Long serviceID, Long providerId) {
        return userOperationsService.createServiceOrder(description, zone, serviceID, providerId);
    }

    @Override
    @GetMapping(path = "/service", produces = APPLICATION_JSON_VALUE)
    public Page<Service> getServices(Pageable pageable) {
        return userOperationsService.getServices(pageable);
    }

    @Override
    @GetMapping(path = "/zoneService", produces = APPLICATION_JSON_VALUE)
    public Page<ServiceOrder> getServicesByZone(Zone zone, Pageable pageable) {
        return userOperationsService.getServicesByZone(zone, pageable);
    }

    @Override
    @GetMapping(path = "/userService", produces = APPLICATION_JSON_VALUE)
    public Page<ServiceOrder> getServicesByUser(User user, Pageable pageable) {
        return userOperationsService.getServicesByUser(user, pageable);
    }

    @Override
    @GetMapping(path = "/orderStatus", produces = APPLICATION_JSON_VALUE)
    public ServiceOrderStatus getServiceOrderStatus(ServiceOrder serviceOrder) {
        return userOperationsService.getServiceOrderStatus(serviceOrder);
    }

    @Override
    @PostMapping(path = "/cancelOrder")
    public ServiceOrder cancelServiceOrder(ServiceOrder serviceOrder) {
        return userOperationsService.cancelServiceOrder(serviceOrder);
    }

    @Override
    @PostMapping(path = "/editOrder")
    public ServiceOrder editServiceOrder(String description, Zone zone, Service service, ServiceOrder serviceOrder) {
        return userOperationsService.editServiceOrder(description, zone, service, serviceOrder);
    }

    @Override
    @PostMapping(path = "/rateOrder")
    public ServiceOrder rateServiceOrder(ServiceOrder serviceOrder, Integer rating) {
        return userOperationsService.rateServiceOrder(serviceOrder, rating);
    }


}
