package popr.interfaces;

import org.springframework.data.domain.Page;
import popr.model.Service;
import popr.model.enums.ServiceOrderStatusDict;

import java.util.List;

public interface ProviderOperationsInterface {

    Page<Service> getServicesByProvider(Long providerId);

    Service addService(Long providerId, String description, int price,
                       int estimatedRealisationTime, String name);

    Service changeServiceStatus(ServiceOrderStatusDict status, String description, Long serviceOrderId);

    List<ServiceOrderStatusDict> getAllServiceOrderStatuses();

}
