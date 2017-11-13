package popr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import popr.interfaces.ProviderOperationsInterface;
import popr.interfaces.UserOperationsInterface;
import popr.model.Service;
import popr.model.ServiceOrder;
import popr.model.User;
import popr.model.Zone;
import popr.repository.ProviderRepository;
import popr.repository.ZoneRepository;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/serviceUser")
@RequiredArgsConstructor
public class ServiceUserController implements ServiceUserManager {
    @Autowired
    private UserOperationsInterface userOperationsService;

    private ZoneRepository zoneRepository;

    @Override
    @PostMapping(path = "/servicesOrder")
    public ServiceOrder createServiceOrder(String description, String zoneId, Long serviceID, Long providerId) {
        popr.model.Zone zone = zoneRepository.findByPostalCode(zoneId);
        return userOperationsService.createServiceOrder(description, zone, serviceID, providerId);
    }

    @Override
    @GetMapping(value = "/services")
    public Page<Service> getServices(Pageable pageable) {
        return userOperationsService.getServices(pageable);
    }

    @Override
    @GetMapping(path = "/servicesbyzone")
    public Page<ServiceOrder> getServicesByZone(Zone zone, Pageable pageable) {
        return userOperationsService.getServicesByZone(zone, pageable);
    }

    @Override
    @GetMapping(path = "/servicesbyUser")
    public Page<ServiceOrder> getServicesByUser(User user, Pageable pageable) {
        return userOperationsService.getServicesByUser(user, pageable);
    }


}
