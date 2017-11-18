package popr.service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import popr.interfaces.UserOperationsInterface;
import popr.model.*;
import popr.repository.ProviderRepository;
import popr.repository.ServiceOrderRepository;
import popr.repository.ServiceOrderStatusRepository;
import popr.repository.ServiceRepository;

import java.time.ZonedDateTime;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class UserOperationsService implements UserOperationsInterface {

    private final ServiceOrderRepository serviceOrderRepository;
    private final ServiceOrderStatusRepository serviceOrderStatusRepository;
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

    @Override
    public ServiceOrderStatus getServiceOrderStatus(ServiceOrder serviceOrder) {
        return serviceOrderStatusRepository.findByServiceOrderAndCurrentIsTrue(serviceOrder);
    }

    @Override
    public ServiceOrder cancelServiceOrder(ServiceOrder serviceOrder) {
        return null;
    }

    @Override
    public ServiceOrder editServiceOrder(String description, Zone zone, Service service, ServiceOrder serviceOrder) {
        if (description != null) {
            serviceOrder.setDescription(description);
        }
        if (zone != null) {
            serviceOrder.setZone(zone);
        }
        if (service != null) {
            serviceOrder.setService(service);
        }

        return serviceOrderRepository.save(serviceOrder);
    }

    @Override
    public ServiceOrder rateServiceOrder(ServiceOrder serviceOrder, Integer rating) {
        serviceOrder.setRating(rating);
        return serviceOrderRepository.save(serviceOrder);
    }
}
