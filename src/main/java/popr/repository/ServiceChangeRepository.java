package popr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import popr.model.Provider;
import popr.model.ServiceChange;

import java.util.List;

public interface ServiceChangeRepository extends JpaRepository<ServiceChange, Long> {

    @Query("SELECT s FROM ServiceChange s WHERE s.validatedBy is NULL")
    List<ServiceChange> findByValidatedByIsNull();

    Page<ServiceChange> findByService_Provider(Provider provider, Pageable pageable);

    @Transactional
    @Modifying
    @Query("update ServiceChange set validatedBy = 1 where id = ?1")
    void validateChangeById(Long l);


    ServiceChange findById(Long id);
}
