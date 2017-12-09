package popr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import popr.model.ServiceType;

public interface ServiceTypeRepository extends JpaRepository<ServiceType, Long> {

    ServiceType findById(Long id);

}
