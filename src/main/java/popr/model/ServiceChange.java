package popr.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString
@EqualsAndHashCode
public class ServiceChange {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    @NotNull
    private Integer price;

    @NotNull
    private Integer estimatedRealisationTime;

    @ManyToOne(optional = false)
    private Service service;

    @ManyToOne
    private Admin validatedBy;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ChangeStatus> statuses = new ArrayList<>();
}
