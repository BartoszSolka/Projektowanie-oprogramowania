package popr.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@ToString
@EqualsAndHashCode
public class Service {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    private Integer price;

    private Integer estimatedRealisationTime;

    @ManyToOne(optional = false)
    private Provider provider;
}
