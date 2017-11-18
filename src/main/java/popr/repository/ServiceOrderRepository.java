package popr.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import popr.model.Provider;
import popr.model.ServiceOrder;
import popr.model.User;
import popr.model.Zone;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {

    Page<ServiceOrder> findByZone(Zone zone, Pageable pageable);

    Page<ServiceOrder> findByOrderedBy(User user, Pageable pageable);

    List<ServiceOrder> findByServiceId(Long providerId);

    ServiceOrder findById(Long serviceId);
}
