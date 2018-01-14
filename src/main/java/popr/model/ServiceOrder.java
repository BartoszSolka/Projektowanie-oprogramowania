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
public class ServiceOrder {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    private String address;

    @JsonIgnore
    private ZonedDateTime creationDate;

    private String ratingDescription;

    private Integer rating;

    //@JsonIgnore
    @ManyToOne(optional = false)
    private Zone zone;

    @ManyToOne
    private Service service;

    //@JsonIgnore
    @OneToOne
    private Person orderedBy;

    //@JsonIgnore
    @ManyToOne
    private Provider provider;

    @PrePersist
    private void prePersist() {
        creationDate = ZonedDateTime.now();
    }

}
