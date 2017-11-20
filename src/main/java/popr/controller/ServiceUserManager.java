package popr.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import popr.model.*;

import java.util.List;

public interface ServiceUserManager {

    ServiceOrder createServiceOrder(String description, String postalCode, Long serviceID, Long providerId);

    Page<Service> getServices(Pageable pageable);

    Page<ServiceOrder> getServiceOrdersByZone(String postalCode, Pageable pageable);

    Page<ServiceOrder> getServiceOrdersByUser(Pageable pageable);

    ServiceOrderStatus getServiceOrderStatus(Long orderId);

    ServiceOrder cancelServiceOrder(ServiceOrder serviceOrder);

    ServiceOrder editServiceOrder(String description, String postalCode, Service service, Long orderId);

    ServiceOrder rateServiceOrder(Long orderId, Integer rating);



}
