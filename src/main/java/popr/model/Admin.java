package popr.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@ToString
@EqualsAndHashCode
public class Admin {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

}
