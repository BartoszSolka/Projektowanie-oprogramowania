package popr.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import popr.model.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface ServiceUserManager {

    ServiceOrder createServiceOrder(String description, String address, String postalCode, Long serviceID);

    Page<Service> getServices(Pageable pageable);

    Page<ServiceOrder> getServiceOrdersByZone(String postalCode, Pageable pageable);

    Page<Zone> getZones(Pageable pageable);

    Page<ServiceOrder> getServiceOrdersByUser(Pageable pageable);

    ServiceOrderStatus getServiceOrderStatus(Long orderId);

    ServiceOrderStatus cancelServiceOrder(Long orderId);

    ServiceOrder editServiceOrder(String description, String postalCode, Long serviceId, Long orderId, String address);

    ServiceOrder rateServiceOrder(Long orderId, Integer rating, String description);

    Complaint createComplaint(String description, Long orderId);

}
