package popr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import popr.model.Provider;

public interface ProviderRepository extends JpaRepository<Provider, Long> {

    Provider findById(Long id);

}
