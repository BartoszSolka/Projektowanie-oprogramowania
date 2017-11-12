package popr.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import popr.model.*;

public interface AdminManager {

    Provider addProvider(Provider providerJson);
    Admin addAdmin(String username, String password);
    User addUser(String email, String name, String surname, Zone zone, String phoneNo, String address, String username, String password);
    void changeStatusOfChange(String changeId, String statusId, String description);

    String getZonesList();
    String getNotValidatedServiceChanges(Pageable pageable);
    String getStatusChangeDictionary();

}
