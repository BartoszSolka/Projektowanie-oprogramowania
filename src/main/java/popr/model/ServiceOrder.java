package popr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

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

    private String StatusDict;

    private String comment;

    //@JsonIgnore
    @ManyToOne(optional = false)
    private Zone zone;

    @ManyToOne
    private Service service;

    @ManyToOne
    @JsonIgnore
    private Provider provider;

    @JsonIgnore
    @OneToMany(mappedBy="serviceOrder", cascade=CascadeType.ALL)
    private List<ServiceOrderStatus> statuses;

    //@JsonIgnore
    @OneToOne
    private Person orderedBy;

    @PrePersist
    private void prePersist() {
        creationDate = ZonedDateTime.now();
    }

}
