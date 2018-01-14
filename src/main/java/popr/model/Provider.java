package popr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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

    private String name;

    private String nip;

    private String phoneNo;

    private String address;

    private String email;

    @ManyToOne(fetch=FetchType.EAGER)
    @Embedded
    private Zone zone;

    private boolean isActive;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provider")
    List<Person> employees = new ArrayList<>();

    @ManyToOne
    List<ServiceOrder> orders = new ArrayList<>();
}
