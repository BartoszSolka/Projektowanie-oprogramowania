package popr.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import popr.model.Service;
import popr.model.ServiceOrder;
import popr.model.User;
import popr.model.Zone;

import java.util.List;

public interface ServiceUserManager {

    ServiceOrder createServiceOrder(String description, String zoneId, Long serviceID, Long providerId);

    Page<Service> getServices(Pageable pageable);

    Page<ServiceOrder> getServicesByZone(Zone zone, Pageable pageable);

    Page<ServiceOrder> getServicesByUser(User user, Pageable pageable);

}
