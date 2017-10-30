package popr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import popr.model.Zone;

import java.util.Optional;

public interface ZoneRepository extends JpaRepository<Zone, Long> {

    Optional<Zone> findByPostalCode(String postalCode);
}
