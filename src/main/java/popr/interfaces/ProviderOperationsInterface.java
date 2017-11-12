package popr.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import popr.model.Provider;
import popr.model.Service;
import popr.model.ServiceOrder;
import popr.model.Zone;
import popr.model.ServiceOrderStatus;
import popr.model.enums.ServiceOrderStatusDict;

import java.util.List;

public interface ProviderOperationsInterface {

    Page<ServiceOrder> getServiceOrdersByProvider(Provider provider, Pageable pageable);

    Service addService(Provider provider, String description, int price,
                       int estimatedRealisationTime, String name);

    Service changeServiceStatus(ServiceOrderStatusDict status, String description, ServiceOrder serviceOrder);

    List<ServiceOrderStatusDict> getAllServiceOrderStatuses();

    List<Zone> getZones();

}
