package popr.interfaces;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import popr.model.*;

public interface UserOperationsInterface {

    ServiceOrder createServiceOrder(String description, String address, String postalCode, Long serviceId, Long providerId);

    Page<Provider> getProviders(Pageable pageable);

    Page<Service> getServices(Pageable pageable);

    Page<ServiceOrder> getServiceOrdersByZone(String postalCode, Pageable pageable);

    Page<ServiceOrder> getServiceOrdersByUser(Pageable pageable);

    ServiceOrderStatus getServiceOrderStatus(Long orderId);

    ServiceOrder cancelServiceOrder(ServiceOrder serviceOrder);

    ServiceOrder editServiceOrder(String description, String postalCode, Service service, Long orderId);

    ServiceOrder rateServiceOrder(Long orderId, Integer rating);
}
