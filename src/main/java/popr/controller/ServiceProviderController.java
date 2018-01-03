package popr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import popr.interfaces.ProviderOperationsInterface;
import popr.model.*;
import popr.model.enums.ServiceOrderStatusDict;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/serviceProvider")
@RequiredArgsConstructor
public class ServiceProviderController implements ServiceProviderOrderManager, ServiceProviderServiceManager {
	@Autowired
	private ProviderOperationsInterface providerOperationsService;

	@Override
	@PostMapping(path = "/{providerId}/services")
	public Service addService(Service service, @PathVariable Long providerId) {
		return providerOperationsService.addService(providerId, service);
	}

	@Override
	@GetMapping(path = "/{providerId}/orders")
	public List<ServiceOrder> listAllOrders(@PathVariable Long providerId) {
		List<ServiceOrder> orders = providerOperationsService.getServiceOrdersByProviderId(providerId);
		return orders;
	}

	@Override
	@PutMapping(path = "/{providerId}/orders/{orderId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ServiceOrderStatus changeOrderStatus(@RequestBody ServiceOrderStatus newStatus, @PathVariable Long orderId) {
		return providerOperationsService.changeServiceOrderStatus(newStatus, orderId);
	}

	@Override
	@GetMapping(path = "/orderStatusDictionary", produces = APPLICATION_JSON_VALUE)
	public List<ServiceOrderStatusDict> orderStatusDictionary() {
		return providerOperationsService.getAllServiceOrderStatuses();
	}

	@Override
	public ServiceOrder getServiceOrderDetails(Long orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@GetMapping(path = "/orders/users/{userId}")
	public Person fetchUserData(@PathVariable Long userId) {
		return providerOperationsService.getUser(userId);
	}

	@Override
	@DeleteMapping(path = "/services/{serviceId}")
	public void removeService(@PathVariable Long serviceId) {
		Service serviceToDelete = new Service();
		serviceToDelete.setId(serviceId);
		providerOperationsService.deleteService(serviceToDelete);
	}

	@Override
	@PutMapping(path="/{providerId}/services/{serviceId}")
	public Service editService(@PathVariable Long providerId, @PathVariable Long serviceId, @RequestBody Service service) {
		service.setId(serviceId);
		Provider provider = new Provider();
		provider.setId(providerId);
		service.setProvider(provider);
		return providerOperationsService.updateService(providerId, service);
	}

	@Override
	@GetMapping(path="/{providerId}/services/{serviceId}")
	public Service getServiceDetails(@PathVariable Long serviceId) {
		return providerOperationsService.getService(serviceId);
	}

	@Override
	@PostMapping(path="/serviceTypes")
	public ServiceType addServiceType(String name, String description) {
		return providerOperationsService.addServiceType(name, description);
	}

	@Override
	@GetMapping(path="/serviceTypes")
	public Page<ServiceType> getServiceTypes(Pageable pageable) {
		// TODO Auto-generated method stub
		return providerOperationsService.getServiceTypes(pageable);
	}

	@Override
	@GetMapping(path="/{providerId}/serviceChanges")
	public Page<ServiceChange> getServiceChanges(@PathVariable Long providerId, Pageable pageable) {
		Provider provider = new Provider();
		provider.setId(providerId);
		return providerOperationsService.getServiceChangesByProvider(provider, pageable);
	}

	@Override
	@GetMapping(path="/{providerId}/services")
	public List<Service> getServices(@PathVariable long providerId) {
		return providerOperationsService.getServices(providerId);
	}

	@Override
	@PutMapping(path="/{providerId}/status")
	public Provider setStatus(Boolean isActive, @PathVariable Long providerId) {
		if(!validId(providerId) || isActive == null) {
			return null;
		}
		Provider provider = new Provider();
		provider.setId(providerId);
		return providerOperationsService.setProviderStatus(isActive, provider);
		
	}

	@Override
	@GetMapping(path="/zones")
	public List<Zone> getZones() {
		// TODO Auto-generated method stub
		return providerOperationsService.getZones();
	}

	@Override
	@PutMapping(path="/{providerId}/zones/{zoneId}")
	public Provider setLocation(@PathVariable Long providerId,@PathVariable Long zoneId) {
		if(!validId(providerId) || !validId(zoneId)) {
			return null;
		}
		Provider provider = new Provider();
		provider.setId(providerId);
		Zone zone = new Zone();
		zone.setId(zoneId);
		
		return providerOperationsService.setLocation(provider, zone);
	}

	private boolean validId(Long id) {
		return id != null && id > 0;
	}
}
