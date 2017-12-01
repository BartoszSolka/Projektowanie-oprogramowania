package popr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
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
    @Expose
    private Long id;

    @ManyToOne
    private ServiceType serviceType;

    @Expose
    private String title;

    private String description;

    private Integer price;

    private String address;

    private Integer estimatedRealisationTime;

    @JsonIgnore
    @ManyToOne(optional = false)
    private Provider provider;
}
