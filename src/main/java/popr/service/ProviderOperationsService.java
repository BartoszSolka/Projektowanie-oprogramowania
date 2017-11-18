package popr.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import popr.interfaces.ProviderOperationsInterface;
import popr.model.*;
import popr.model.enums.ServiceOrderStatusDict;
import popr.repository.*;

import java.util.Arrays;
import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ProviderOperationsService implements ProviderOperationsInterface {

    private final ServiceOrderRepository serviceOrderRepository;
    private final ServiceRepository serviceRepository;
    private final ServiceOrderStatusRepository serviceOrderStatusRepository;
    private final ServiceChangeRepository serviceChangeRepository;
    private final UserRepository userRepository;
    private final ServiceTypeRepository serviceTypeRepository;
    private final ProviderRepository providerRepository;
    private final ZoneRepository zoneRepository;

    @Override
    public List<ServiceOrder> getServiceOrdersByProviderId(Long providerId) {
    	List<Service> services = serviceRepository.findByProviderId(providerId);
    	List<ServiceOrder> orders = serviceOrderRepository.findByServiceId(services.get(0).getId());
        return orders;
    }

    @Override
    public Service addService(Long providerId, Service service) {
    	Provider provider = new Provider();
    	provider.setId(providerId);
    	service.setProvider(provider);
        return serviceRepository.save(service);
    }

    @Override
    public ServiceOrderStatus changeServiceOrderStatus(ServiceOrderStatus newStatusOrder, Long serviceOrderId) {
    	ServiceOrder updatedServiceOrder = new ServiceOrder();
    	updatedServiceOrder.setId(serviceOrderId);
    	newStatusOrder.setServiceOrder(updatedServiceOrder);
    	return serviceOrderStatusRepository.save(newStatusOrder);
    }

    @Override
    public List<ServiceOrderStatusDict> getAllServiceOrderStatuses() {
        return Arrays.asList(ServiceOrderStatusDict.values());
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.findOne(userId);
    }

    @Override
    public ServiceType addServiceType(String name, String description) {
        return serviceTypeRepository.save(new ServiceType(name, description));
    }

    @Override
    public Page<ServiceType> getServiceTypes(Pageable pageable) {
        return serviceTypeRepository.findAll(pageable);
    }

    @Override
    public Service editService(Long providerId, Integer price, Integer estimatedRealisationTime, ServiceType serviceType, Service serviceToEdit) {
        if (!serviceToEdit.getProvider().getId().equals(providerId)) {
            return null;//not able to edit
        }

        if (price != null) {
            serviceToEdit.setPrice(price);
        }

        if (estimatedRealisationTime != null) {
            serviceToEdit.setEstimatedRealisationTime(estimatedRealisationTime);
        }

        if (serviceType != null) {
            serviceToEdit.setServiceType(serviceType);
        }

        return serviceRepository.save(serviceToEdit);
    }

    @Override
    public void deleteService(Service service) {
        serviceRepository.delete(service);
    }

    @Override
    public Page<ServiceChange> getServiceChangesByProvider(Provider provider, Pageable pageable) {
        return serviceChangeRepository.findByService_Provider(provider, pageable);
    }

    @Override
    public Provider setProviderStatus(boolean isActive, Provider provider) {
        provider.setActive(isActive);
        return providerRepository.save(provider);
    }

    @Override
    public List<Zone> getZones() {
        return zoneRepository.findAll();
    }

    @Override
    public Provider setLocation(Provider provider, Zone zone) {
        provider.setZone(zone);
        return providerRepository.save(provider);
    }
}
