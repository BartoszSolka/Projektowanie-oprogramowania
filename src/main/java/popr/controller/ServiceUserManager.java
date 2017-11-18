package popr.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import popr.model.*;

import java.util.List;

public interface ServiceUserManager {

    ServiceOrder createServiceOrder(String description, Zone zone, Long serviceID, Long providerId);

    Page<Service> getServices(Pageable pageable);

    Page<ServiceOrder> getServicesByZone(Zone zone, Pageable pageable);

    Page<ServiceOrder> getServicesByUser(User user, Pageable pageable);

    ServiceOrderStatus getServiceOrderStatus(ServiceOrder serviceOrder);

    ServiceOrder cancelServiceOrder(ServiceOrder serviceOrder);

    ServiceOrder editServiceOrder(String description, Zone zone, Service service, ServiceOrder serviceOrder);

    ServiceOrder rateServiceOrder(ServiceOrder serviceOrder, Integer rating);



}
