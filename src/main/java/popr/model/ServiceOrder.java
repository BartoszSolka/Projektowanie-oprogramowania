package popr.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    private Integer rating;

    @JsonIgnore
    @ManyToOne(optional = false)
    private Zone zone;

    @JsonIgnore
    @ManyToOne
    private Service service;

    @JsonIgnore
    @OneToOne
    private User orderedBy;

    @PrePersist
    private void prePersist() {
        creationDate = ZonedDateTime.now();
    }

}
