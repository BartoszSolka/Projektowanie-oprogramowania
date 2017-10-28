package popr.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.geo.Point;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString
@EqualsAndHashCode
public class Provider {

    @Id
    @GeneratedValue
    private Long id;

    private String nip;

    private String phoneNo;

    private String address;

    @Embedded
    private Point location;

    @OneToMany(cascade = CascadeType.ALL)
    List<Employee> employees = new ArrayList<>();
}
