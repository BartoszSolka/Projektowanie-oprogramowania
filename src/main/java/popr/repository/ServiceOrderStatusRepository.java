package popr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import popr.model.ServiceOrder;
import popr.model.ServiceOrderStatus;

import java.util.List;

public interface ServiceOrderStatusRepository extends JpaRepository<ServiceOrderStatus, Long> {

    ServiceOrderStatus findByServiceOrderAndCurrentIsTrue(ServiceOrder serviceOrder);

    @Transactional
    void deleteByServiceOrderId(Long id);

    @Query("select s from ServiceOrderStatus s where s.serviceOrder.id = ?1")
    List<ServiceOrderStatus> findByServiceOrderId(Long serviceOrderId);
}
