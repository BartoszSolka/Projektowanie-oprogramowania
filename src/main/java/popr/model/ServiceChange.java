package popr.model;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
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
    @Expose
    private Long id;

    @Expose
    @Getter
    private String description;

    @NotNull
    @Expose
    private Integer price;

    @NotNull
    @Expose
    private Integer estimatedRealisationTime;

    @ManyToOne(optional = false)
    @Expose
    private Service service;

    @ManyToOne
    @Expose
    private Admin validatedBy;

    @OneToMany(cascade = CascadeType.ALL)
    @Expose
    private List<ChangeStatus> statuses = new ArrayList<>();
}
