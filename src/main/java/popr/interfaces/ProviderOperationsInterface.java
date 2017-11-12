package popr.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import popr.model.Provider;
import popr.model.Service;
import popr.model.ServiceOrder;
import popr.model.ServiceOrderStatus;
import popr.model.enums.ServiceOrderStatusDict;

import java.util.List;

public interface ProviderOperationsInterface {

    List<ServiceOrderStatusDict> getAllServiceOrderStatuses();

	List<ServiceOrder> getServiceOrdersByProviderId(Long providerId);

	Service addService(Long providerId, Service service);

	ServiceOrderStatus changeServiceOrderStatus(ServiceOrderStatus newStatusOrder, Long serviceOrderId);

}
