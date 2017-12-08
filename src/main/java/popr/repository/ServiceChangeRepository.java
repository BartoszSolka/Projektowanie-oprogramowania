package popr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import popr.model.Provider;
import popr.model.ServiceChange;

import java.util.List;

public interface ServiceChangeRepository extends JpaRepository<ServiceChange, Long> {

    @Query("SELECT s FROM ServiceChange s WHERE s.validatedBy is NULL")
    List<ServiceChange> findByValidatedByIsNull();

    Page<ServiceChange> findByService_Provider(Provider provider, Pageable pageable);

    //void changeStatusOfChange(Long changeId, Long statusId, String description);
}
