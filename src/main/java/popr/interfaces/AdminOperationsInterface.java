package popr.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import popr.model.*;

public interface AdminOperationsInterface {

    Provider addProvider(Provider provider);
    Admin addAdmin(String username, String password);
    User addUser(String email, String name, String surname, Zone zone, String phoneNo, String address, String username, String password);
    void changeStatusOfChange(String changeId, String statusId, String description);

    String getZonesList();
    Page<ServiceChange> getNotValidatedServiceChanges(Pageable pageable);
    Page<ServiceOrderStatus> getStatusChangeDictionary();
}
