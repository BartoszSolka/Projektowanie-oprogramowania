package popr.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@ToString
@EqualsAndHashCode
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @Email
    private String email;

    private String name;

    private String surname;

    private String phoneNo;

    private String address;

    private String username;

    private String password;

    @ManyToOne(optional = false)
    private Zone zone;
}
