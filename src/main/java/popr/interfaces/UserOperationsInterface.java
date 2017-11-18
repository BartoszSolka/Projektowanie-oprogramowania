package popr.interfaces;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import popr.model.*;

public interface UserOperationsInterface {

    ServiceOrder createServiceOrder(String description, Zone zone, Long serviceId, Long providerId);

    Page<Provider> getProviders(Pageable pageable);

    Page<Service> getServices(Pageable pageable);

    Page<ServiceOrder> getServicesByZone(Zone zone, Pageable pageable);

    Page<ServiceOrder> getServicesByUser(User user, Pageable pageable);

    ServiceOrderStatus getServiceOrderStatus(ServiceOrder serviceOrder);

    ServiceOrder cancelServiceOrder(ServiceOrder serviceOrder);

    ServiceOrder editServiceOrder(String description, Zone zone, Service service, ServiceOrder serviceOrder);

    ServiceOrder rateServiceOrder(ServiceOrder serviceOrder, Integer rating);
}
