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
@Table(name = "users")
public class User {

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

    @ManyToOne
    private Zone zone;
}
