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
public class ServiceOrder {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    private ZonedDateTime creationDate;

    private Integer rating;

    @ManyToOne(optional = false)
    private Zone zone;

    @ManyToOne
    private Service service;

    @PrePersist
    private void prePersist() {
        creationDate = ZonedDateTime.now();
    }

}
