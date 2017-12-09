package popr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@ToString
@EqualsAndHashCode
public class ChangeStatus {

    @Id
    @GeneratedValue
    @Expose
    private Long id;

    @Expose
    private String comment;

    @Expose
    private boolean current = true;

    @ManyToOne
    @Expose
    @JsonIgnore
    private ServiceChange serviceChange;
}
