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
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String surname;

    @ManyToOne(optional = false)
    private Provider provider;
}
