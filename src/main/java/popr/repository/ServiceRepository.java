package popr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import popr.model.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {
	List<Service> findByProviderId(Long id);
	Service findById(Long id);
}
