package popr.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@ToString
@EqualsAndHashCode
@Embeddable
public class Zone {

    @Id
    @GeneratedValue
    private Long id;

    private String postalCode;
}
