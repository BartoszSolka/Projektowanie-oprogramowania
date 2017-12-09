package popr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import popr.model.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    @Transactional
    void deleteByServiceOrderId(Long serviceId);

}
