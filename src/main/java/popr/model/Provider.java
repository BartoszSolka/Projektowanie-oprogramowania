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

    @Embedded
    private Location location;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provider")
    List<Employee> employees = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getNip() {
        return nip;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getAdress() {
        return address;
    }

    public Location getLocation() {
        return location;
    }
}
