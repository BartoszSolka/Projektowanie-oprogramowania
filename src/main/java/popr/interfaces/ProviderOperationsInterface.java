package popr.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import popr.model.*;
import popr.model.enums.ServiceChangeType;
import popr.model.enums.ServiceOrderStatusDict;

import java.util.List;

public interface ProviderOperationsInterface {
	
	ServiceOrder getServiceOrderDetails(Long orderId);

    List<ServiceOrderStatusDict> getAllServiceOrderStatuses();

	List<ServiceOrder> getServiceOrdersByProviderId(Long providerId);

	Service addService(Long providerId, Service service);

	ServiceOrderStatus changeServiceOrderStatus(ServiceOrderStatus newStatusOrder, Long serviceOrderId);

	Person getUser(Long userId);

	ServiceType addServiceType(String name, String description);

	Page<ServiceType> getServiceTypes(Pageable pageable);

	Service editService(Long providerId, Integer price, Integer estimatedRealisationTime, ServiceType serviceType, Service serviceToEdit);

	void deleteService(Service service);

	Page<ServiceChange> getServiceChangesByProvider(Provider provider, Pageable pageable);

	Provider setProviderStatus(boolean isActive, Provider provider);

	List<Zone> getZones();

	Provider setLocation(Provider provider, Zone zone);

	ServiceChange addServiceChange(Provider provider, Integer price, Integer estimatedRealisationTime, ServiceType serviceType, Service service, ServiceChangeType serviceChangeType);

	Service updateService(Long providerId, Service serviceToEdit);
	
	Service getService(Long serviceId);
	
	List<Service> getServices(Long providerId);
	
	Zone getZone(Long providerId);

	ServiceChange addServiceChange(Long provider, ServiceChange serviceChange);
}
