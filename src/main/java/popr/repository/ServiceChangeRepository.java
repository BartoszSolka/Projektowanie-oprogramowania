package popr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import popr.model.ServiceChange;

public interface ServiceChangeRepository extends JpaRepository<ServiceChange, Long> {

    Page<ServiceChange> findByValidatedByIsNull(Pageable pageable);
}
