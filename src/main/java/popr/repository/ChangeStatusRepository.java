package popr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import popr.model.ChangeStatus;

public interface ChangeStatusRepository extends JpaRepository<ChangeStatus, Long> {



}
