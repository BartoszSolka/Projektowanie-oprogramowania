package popr.controller;

import popr.model.Service;
import popr.model.ServiceOrder;

public interface ServiceProviderServiceManager {
	
	Service addService(Service service, Long providerId);
	
	ServiceOrder getServiceOrderDetails(Long serviceId);
	
	Service getServiceDetails(Long serviceId);
	
	void removeService(Long serviceId);

	Service editService(Long providerId, Long serviceId, Service service);
}
