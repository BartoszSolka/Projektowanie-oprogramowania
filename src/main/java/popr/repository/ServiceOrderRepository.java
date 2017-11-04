package popr.repository;

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

    Page<ServiceOrder> findByService_Provider(Provider provider, Pageable pageable);
}
