package popr.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import popr.model.*;

import java.util.List;

public interface ServiceUserManager {

    ServiceOrder createServiceOrder(String description, Zone zonee, Long serviceID, Long providerId);

    Page<Service> getServices(Pageable pageable);

    Page<ServiceOrder> getServicesByZone(Zone zone, Pageable pageable);

    Page<ServiceOrder> getServicesByUser(User user, Pageable pageable);

    ServiceOrderStatus getServiceOrderStatus(Long orderId);

    ServiceOrder cancelServiceOrder(ServiceOrder serviceOrder);

    ServiceOrder editServiceOrder(String description, Zone zone, Service service, Long orderId);

    ServiceOrder rateServiceOrder(Long orderId, Integer rating);



}
