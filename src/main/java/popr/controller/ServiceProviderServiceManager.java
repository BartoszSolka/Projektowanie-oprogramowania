package popr.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import popr.model.Provider;
import popr.model.Service;
import popr.model.ServiceChange;
import popr.model.ServiceOrder;
import popr.model.ServiceType;
import popr.model.Zone;

public interface ServiceProviderServiceManager {
	
	Service addService(Service service, Long providerId);
	
	ServiceOrder getServiceOrderDetails(Long serviceId);
	
	Service getServiceDetails(Long serviceId);
	
	void removeService(Long serviceId);

	Service editService(Long providerId, Long serviceId, Service service);
	
	ServiceType addServiceType(String name, String description);
	
	Page<ServiceType> getServiceTypes(Pageable pageable);
	
	Page<ServiceChange> getServiceChanges(Long providerId, Pageable pageable);
	
	List<Service> getServices(long providerId);
	
	Provider setStatus(Boolean isActive, Long providerId);
	
	List<Zone> getZones();
	
	Provider setLocation(Long providerId, Long zoneId);
}
