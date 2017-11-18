package popr.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class ServiceType {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    public ServiceType(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
