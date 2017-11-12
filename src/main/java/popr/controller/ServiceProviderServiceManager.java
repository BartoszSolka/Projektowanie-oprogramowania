package popr.controller;

import popr.model.Service;

public interface ServiceProviderServiceManager {

	Service addService(Service service, Long providerId);
}
