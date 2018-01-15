package popr.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import popr.model.Provider;
import popr.model.Service;
import popr.model.ServiceType;

public interface ServiceRepository extends JpaRepository<Service, Long> {

	List<Service> findByProviderId(Long id);
	List<Service> findByServiceType(ServiceType serviceType);

	Service findById(Long id);
	@Transactional
	@Modifying
	@Query("delete Service s where s.provider.id = ?1")
	void deleteByProviderId(Long id);

}
