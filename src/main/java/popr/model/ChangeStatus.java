package popr.model;

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

    private Long id;

    private String comment;

    private boolean current = true;

    @ManyToOne
    private ServiceChange serviceChange;
}
