package popr.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import popr.model.*;

import java.util.List;

public interface AdminOperationsInterface {

    Provider addProvider(String nip, String phoneNo, String address, String name, Zone zone);

    Page<ServiceChange> getNotValidatedServiceChanges(Pageable pageable);

    User addUser(String email, String name, String surname, Zone zone, String phoneNo,
                 String address, String username, String password);

    Admin addAdmin(String username, String password);

    List<Zone> getZones();
}
