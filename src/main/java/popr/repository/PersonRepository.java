package popr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import popr.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByUsername(String username);
}