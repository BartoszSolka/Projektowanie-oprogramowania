package popr.service;

import lombok.RequiredArgsConstructor;
import popr.interfaces.ProviderOperationsInterface;
import popr.model.Provider;
import popr.model.Service;
import popr.model.ServiceOrder;
import popr.model.ServiceOrderStatus;
import popr.model.ServiceOrderStatus;
import popr.model.enums.ServiceOrderStatusDict;
import popr.repository.ServiceOrderRepository;
import popr.repository.ServiceOrderStatusRepository;
import popr.repository.ServiceRepository;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ProviderOperationsService implements ProviderOperationsInterface {

    private final ServiceOrderRepository serviceOrderRepository;
    private final ServiceRepository serviceRepository;
    private final ServiceOrderStatusRepository serviceOrderStatusRepository;
    private final ServiceOrderStatusRepository serviceOrderStatusRepository;

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
}
