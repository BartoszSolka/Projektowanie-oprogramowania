package popr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private ZonedDateTime creationDate;

    private boolean current = true;

    @ManyToOne(optional = false)
    private ServiceOrder serviceOrder;

    @JsonIgnore
    @ManyToOne(optional = false)
    private Person createdBy;

    @PrePersist
    private void prePersist() {
        creationDate = ZonedDateTime.now();
    }
}
