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
public class Complaint {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    private ZonedDateTime creationDate;

    private boolean current = true;

    @ManyToOne(optional = false)
    private ServiceOrder serviceOrder;

    @ManyToOne(optional = false)
    private User createdBy;

    @PrePersist
    private void prePersist() {
        creationDate = ZonedDateTime.now();
    }
}
