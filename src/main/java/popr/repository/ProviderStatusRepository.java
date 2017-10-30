package popr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import popr.model.Provider;

public interface ProviderStatusRepository extends JpaRepository<Provider, Long> {
}
