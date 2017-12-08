package popr.controller;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import popr.interfaces.ProviderOperationsInterface;
import popr.model.Provider;
import popr.model.Service;
import popr.model.ServiceOrder;
import popr.model.ServiceOrderStatus;
import popr.model.User;
import popr.model.enums.ServiceOrderStatusDict;

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
	public User fetchUserData(@PathVariable Long userId) {
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
	
	
}
