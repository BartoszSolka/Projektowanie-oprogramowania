package popr.controller;

import popr.model.Person;
import popr.model.ServiceOrder;
import popr.model.ServiceOrderStatus;
import popr.model.enums.ServiceOrderStatusDict;

import java.util.List;

public interface ServiceProviderOrderManager {
	List<ServiceOrder> listAllOrders(Long serviceProviderId);
	
	List<ServiceOrderStatusDict> orderStatusDictionary();

	ServiceOrderStatus changeOrderStatus(ServiceOrderStatus newStatus, Long orderId);
	
	Person fetchUserData(Long userId);

}
