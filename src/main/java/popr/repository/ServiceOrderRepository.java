package popr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import popr.model.ServiceOrder;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {
}
