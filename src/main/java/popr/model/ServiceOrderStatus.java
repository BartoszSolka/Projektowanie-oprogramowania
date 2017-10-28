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
public class ServiceOrderStatus {

    @Id
    @GeneratedValue
    private Long id;

    private String comment;

    private ZonedDateTime creationDate;

    private boolean current = true;

    @ManyToOne(optional = false)
    private ServiceOrder serviceOrder;

    @PrePersist
    private void prePersist() {
        creationDate = ZonedDateTime.now();
    }
}
