package popr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne
    private ServiceType serviceType;
    
    private String title;

    private String description;

    private Integer price;

    private Integer estimatedRealisationTime;

    @JsonIgnore
    @ManyToOne(optional = false)
    private Provider provider;
}
