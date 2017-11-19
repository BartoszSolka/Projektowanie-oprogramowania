package popr.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import popr.model.*;

public interface AdminManager {

    Provider addProvider(Provider providerJson);
    Admin addAdmin(Admin admin);
    User addUser(User user);
    void changeStatusOfChange(String changeId, String statusId, String description);

    String getZonesList();
    String getNotValidatedServiceChanges();
    String getStatusChangeDictionary();

}