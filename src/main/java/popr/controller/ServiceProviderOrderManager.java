package popr.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;

import popr.model.Service;
import popr.model.ServiceOrder;
import popr.model.ServiceOrderStatus;
import popr.model.enums.ServiceOrderStatusDict;

public interface ServiceProviderOrderManager {
	List<ServiceOrder> listAllOrders(Long serviceProviderId);
	
	List<ServiceOrderStatusDict> orderStatusDictionary();

	ServiceOrderStatus changeOrderStatus(ServiceOrderStatus newStatus, Long orderId);
}
