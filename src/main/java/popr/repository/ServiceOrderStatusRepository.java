package popr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import popr.model.ServiceOrder;
import popr.model.ServiceOrderStatus;

public interface ServiceOrderStatusRepository extends JpaRepository<ServiceOrderStatus, Long> {

    ServiceOrderStatus findByServiceOrderAndCurrentIsTrue(ServiceOrder serviceOrder);

    @Transactional
    void deleteByServiceOrderId(Long id);
}
