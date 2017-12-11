package popr.interfaces;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import popr.model.*;

public interface UserOperationsInterface {

    ServiceOrder createServiceOrder(String description, String address, Zone zone, Long serviceID);

    Page<Service> getServices(Pageable pageable);

    Page<Provider> getProviders(Pageable pageable);

    Page<ServiceOrder> getServiceOrdersByZone(Zone zone, Pageable pageable);

    Page<ServiceOrder> getServiceOrdersByUser(Pageable pageable);

    ServiceOrderStatus getServiceOrderStatus(ServiceOrder serviceOrder);

    ServiceOrderStatus cancelServiceOrder(Long orderId);

    ServiceOrder editServiceOrder(String description, String postalCode, Long serviceId, Long orderId, String address);

    ServiceOrder rateServiceOrder(Long orderId, Integer rating, String description);

    Complaint createComplaint(String description, Long orderId);
}
