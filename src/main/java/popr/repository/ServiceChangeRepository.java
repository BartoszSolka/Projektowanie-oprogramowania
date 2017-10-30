package popr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import popr.model.ServiceChange;

public interface ServiceChangeRepository extends JpaRepository<ServiceChange, Long> {
}
