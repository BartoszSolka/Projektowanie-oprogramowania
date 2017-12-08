package popr.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;

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

    @Column(unique = true)
    private String username;

    private String password;

    @ManyToOne
    private Zone zone;

    private boolean isAdmin;

    @ManyToOne
    private Provider provider;
}
