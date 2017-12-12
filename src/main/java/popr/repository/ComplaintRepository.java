package popr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import popr.model.Complaint;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    @Transactional
    void deleteByServiceOrderId(Long serviceId);

    @Query("select c from Complaint c where c.serviceOrder.id = ?1")
    List<Complaint> findByServiceOrderId(Long serviceId);
}
