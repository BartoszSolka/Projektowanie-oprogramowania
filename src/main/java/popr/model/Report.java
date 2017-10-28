package popr.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Data
@ToString
@EqualsAndHashCode
public class Report {

    @Id
    @GeneratedValue
    private Long id;

    private ZonedDateTime creationDate;

    private String content;

    @ManyToOne(optional = false)
    private ServiceOrder serviceOrder;

    @PrePersist
    private void prePersist() {
        creationDate = ZonedDateTime.now();
    }
}
