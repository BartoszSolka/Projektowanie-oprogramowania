package popr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import popr.model.Complaint;
import popr.model.Person;
import popr.model.ServiceOrder;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    Page<Complaint> findByServiceOrder(ServiceOrder serviceOrder, Pageable pageable);

    @Transactional
    void deleteByServiceOrderId(Long serviceId);

    @Query("select c from Complaint c where c.serviceOrder.id = ?1")
    List<Complaint> findByServiceOrderId(Long serviceId);
}
