package popr.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import popr.model.*;
import popr.model.enums.ServiceOrderStatusDict;

import java.util.List;

public interface ProviderOperationsInterface {

    List<ServiceOrderStatusDict> getAllServiceOrderStatuses();

	List<ServiceOrder> getServiceOrdersByProviderId(Long providerId);

	Service addService(Long providerId, Service service);

	ServiceOrderStatus changeServiceOrderStatus(ServiceOrderStatus newStatusOrder, Long serviceOrderId);

	User getUser(Long userId);

	ServiceType addServiceType(String name, String description);

	Page<ServiceType> getServiceTypes(Pageable pageable);

	Service editService(Long providerId, Integer price, Integer estimatedRealisationTime, ServiceType serviceType, Service serviceToEdit);

	void deleteService(Service service);

	Page<ServiceChange> getServiceChangesByProvider(Provider provider, Pageable pageable);

	Provider setProviderStatus(boolean isActive, Provider provider);

	List<Zone> getZones();

	Provider setLocation(Provider provider, Zone zone);
}
