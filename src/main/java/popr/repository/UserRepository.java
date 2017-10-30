package popr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import popr.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
