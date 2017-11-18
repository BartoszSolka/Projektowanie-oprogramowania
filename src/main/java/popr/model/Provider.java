package popr.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString(exclude="employees")
@EqualsAndHashCode
public class Provider {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String nip;

    private String phoneNo;

    private String address;

    @ManyToOne
    private Zone zone;

    private boolean isActive;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provider")
    List<Employee> employees = new ArrayList<>();
}
