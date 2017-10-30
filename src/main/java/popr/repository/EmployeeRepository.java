package popr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import popr.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
