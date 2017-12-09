package popr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import popr.model.Person;
import popr.model.ServiceOrder;
import popr.model.Zone;

import java.util.List;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {

    Page<ServiceOrder> findByZone(Zone zone, Pageable pageable);

    Page<ServiceOrder> findByOrderedBy(Person user, Pageable pageable);

    List<ServiceOrder> findByServiceId(Long providerId);

    ServiceOrder findById(Long serviceId);

    @Transactional
    void deleteByServiceId(Long serviceId);
}
