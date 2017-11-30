package popr.service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import popr.interfaces.UserOperationsInterface;
import popr.model.*;
import popr.repository.*;

import java.time.ZonedDateTime;



@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class UserOperationsService implements UserOperationsInterface {

    private final ServiceOrderRepository serviceOrderRepository;
    private final ServiceOrderStatusRepository serviceOrderStatusRepository;
    private final ServiceRepository serviceRepository;
    private final UserService userService;
    private final ZoneRepository zoneRepository;
    private final ProviderRepository providerRepository;

    @Override
    public ServiceOrder createServiceOrder(String description, String address, String postalCode, Long serviceId, Long providerId) {
        popr.model.Service service = serviceRepository.findOne(serviceId);
        Zone zone = zoneRepository.findByPostalCode(postalCode);
        if (service == null) {
            return null;//handle error
        }

        ServiceOrder serviceOrder = new ServiceOrder();

        serviceOrder.setDescription(description);
        serviceOrder.setCreationDate(ZonedDateTime.now());
        serviceOrder.setAddress(address);
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
    public Page<ServiceOrder> getServiceOrdersByZone(String postalCode, Pageable pageable) {
        Zone zone = zoneRepository.findByPostalCode(postalCode);
        return serviceOrderRepository.findByZone(zone, pageable);
    }

    @Override
    public Page<ServiceOrder> getServiceOrdersByUser(Pageable pageable) {
        User user = userService.readCurrent();
        return serviceOrderRepository.findByOrderedBy(user, pageable);
    }

    @Override
    public ServiceOrderStatus getServiceOrderStatus(Long orderId) {
        ServiceOrder serviceOrder = serviceOrderRepository.findById(orderId);
        return serviceOrderStatusRepository.findByServiceOrderAndCurrentIsTrue(serviceOrder);
    }

    @Override
    public ServiceOrder cancelServiceOrder(ServiceOrder serviceOrder) {
        return null;
    }

    @Override
    public ServiceOrder editServiceOrder(String description, String postalCode, Service service, Long orderId) {
        ServiceOrder serviceOrder = serviceOrderRepository.findById(orderId);
        Zone zone = zoneRepository.findByPostalCode(postalCode);
        if (description != null) {
            serviceOrder.setDescription(description);
        }
        if (postalCode != null) {
            serviceOrder.setZone(zone);
        }
        if (service != null) {
            serviceOrder.setService(service);
        }

        return serviceOrderRepository.save(serviceOrder);
    }

    @Override
    public ServiceOrder rateServiceOrder(Long orderId, Integer rating) {
        ServiceOrder serviceOrder = serviceOrderRepository.findById(orderId);
        serviceOrder.setRating(rating);
        return serviceOrderRepository.save(serviceOrder);
    }
}
