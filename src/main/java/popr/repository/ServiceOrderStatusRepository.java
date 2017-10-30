package popr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import popr.model.ServiceOrderStatus;

public interface ServiceOrderStatusRepository extends JpaRepository<ServiceOrderStatus, Long> {
}
