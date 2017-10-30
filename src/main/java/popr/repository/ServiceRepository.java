package popr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import popr.model.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
