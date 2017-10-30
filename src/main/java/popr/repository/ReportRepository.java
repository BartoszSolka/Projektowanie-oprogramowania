package popr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import popr.model.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
