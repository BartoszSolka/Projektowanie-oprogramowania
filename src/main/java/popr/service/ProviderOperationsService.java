package popr.service;
import popr.model.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import popr.interfaces.ProviderOperationsInterface;
import popr.model.*;
import popr.model.enums.ServiceChangeType;
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
    private final PersonRepository personRepository;
    private final ServiceTypeRepository serviceTypeRepository;
    private final ProviderRepository providerRepository;
    private final ZoneRepository zoneRepository;
    private final MailServiceImpl mailService;
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
        String mailContent;
        mailContent = ("Zmieniono status zlecenia" + "\n" + "Opis: " + updatedServiceOrder.getDescription() + "\n" + "Nowy status: " + newStatusOrder.getOrderStatusDict() + "\n" + "Opis nowwego statusu: " + newStatusOrder.getComment());
        mailService.sendEmail(mailContent, updatedServiceOrder.getOrderedBy().getEmail());

        return serviceOrderStatusRepository.save(newStatusOrder);
    }

    @Override
    public List<ServiceOrderStatusDict> getAllServiceOrderStatuses() {
        return Arrays.asList(ServiceOrderStatusDict.values());
    }

    @Override
    public Person getUser(Long userId) {
        return personRepository.findOne(userId);
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
    public Service updateService(Long providerId, Service serviceToEdit) {
        if (!serviceToEdit.getProvider().getId().equals(providerId)) {
            return null;//not able to edit
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
    	Provider updatedProvider = providerRepository.findById(provider.getId());
    	
        updatedProvider.setActive(isActive);
        return providerRepository.save(updatedProvider);
    }

    @Override
    public List<Zone> getZones() {
        return zoneRepository.findAll();
    }

    @Override
    public Provider setLocation(Provider provider, Zone zone) {
    	Provider updatedProvider = providerRepository.findById(provider.getId());
    	updatedProvider.setZone(zone);
        return providerRepository.save(updatedProvider);
    }

    @Override
    public ServiceChange addServiceChange(Provider provider, Integer price, Integer estimatedRealisationTime, ServiceType serviceType, Service service, ServiceChangeType serviceChangeType) {
        ServiceChange serviceChange = new ServiceChange();

        serviceChange.setId(provider.getId());
        serviceChange.setServiceChangeType(serviceChangeType);
        serviceChange.setPrice(price);
        serviceChange.setEstimatedRealisationTime(estimatedRealisationTime);
        serviceChange.setService(service);
        return serviceChangeRepository.save(serviceChange);
    }

	@Override
	public ServiceOrder getServiceOrderDetails(Long orderId) {
		return serviceOrderRepository.findOne(orderId);
	}

	@Override
	public Service getService(Long serviceId) {
		return serviceRepository.findOne(serviceId);
	}

	@Override
	public List<Service> getServices(Long providerId) {
		Provider provider = new Provider();
		provider.setId(providerId);
		
		return serviceRepository.findByProviderId(providerId);
	}
}
