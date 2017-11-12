package popr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import popr.interfaces.ProviderOperationsInterface;
import popr.interfaces.UserOperationsInterface;
import popr.model.Service;
import popr.model.ServiceOrder;
import popr.model.User;
import popr.model.Zone;
import popr.repository.ProviderRepository;
import popr.repository.ZoneRepository;

import java.util.List;

public class ServiceUserController implements ServiceUserManager {
    @Autowired
    private UserOperationsInterface userOperationsService;

    private ZoneRepository zoneRepository;

    @Override
    public ServiceOrder createServiceOrder(String description, String zoneId, Long serviceID, Long providerId) {
        popr.model.Zone zone = zoneRepository.findByPostalCode(zoneId);
        return userOperationsService.createServiceOrder(description, zone, serviceID, providerId);
    }

    @Override
    public Page<Service> getServices(Pageable pageable) {
        return userOperationsService.getServices(pageable);
    }

    @Override
    public Page<ServiceOrder> getServicesByZone(Zone zone, Pageable pageable) {
        return userOperationsService.getServicesByZone(zone, pageable);
    }

    @Override
    public Page<ServiceOrder> getServicesByUser(User user, Pageable pageable) {
        return userOperationsService.getServicesByUser(user, pageable);
    }


}
