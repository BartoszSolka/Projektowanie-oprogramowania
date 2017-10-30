package popr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import popr.model.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
}
