package popr.service;
import java.time.ZonedDateTime;
import popr.model.User;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import popr.interfaces.UserOperationsInterface;
import popr.model.Provider;
import popr.model.ServiceOrder;
import popr.model.Zone;
import popr.repository.ProviderRepository;
import popr.repository.ServiceOrderRepository;
import popr.repository.ServiceRepository;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class UserOperationsService implements UserOperationsInterface {

    private final ServiceOrderRepository serviceOrderRepository;
    private final ServiceRepository serviceRepository;
    private final UserService userService;
    private final ProviderRepository providerRepository;

    @Override
    public ServiceOrder createServiceOrder(String description, Zone zone, Long serviceId, Long providerId) {
        popr.model.Service service = serviceRepository.findOne(serviceId);
        if (service == null) {
            return null;//handle error
        }

        ServiceOrder serviceOrder = new ServiceOrder();

        serviceOrder.setDescription(description);
        serviceOrder.setCreationDate(ZonedDateTime.now());
        serviceOrder.setZone(zone);
        serviceOrder.setService(service);
        serviceOrder.setOrderedBy(userService.readCurrent());

        return serviceOrderRepository.save(serviceOrder);
    }

    @Override
    public Page<Provider> getProviders(Pageable pageable) {
        return providerRepository.findAll(pageable);
    }

    @Override
    public Page<popr.model.Service> getServices(Pageable pageable) {
        return serviceRepository.findAll(pageable);
    }

    @Override
    public Page<ServiceOrder> getServicesByZone(Zone zone, Pageable pageable) {
        return serviceOrderRepository.findByZone(zone, pageable);
    }

    @Override
    public Page<ServiceOrder> getServicesByUser(User user, Pageable pageable) {
        return serviceOrderRepository.findByOrderedBy(user, pageable);
    }
}
