package popr.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    @ManyToOne
    private Zone zone;

    private boolean isAdmin;

    @ManyToOne
    private Provider provider;

}
