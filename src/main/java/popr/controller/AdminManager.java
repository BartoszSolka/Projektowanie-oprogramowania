package popr.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import popr.model.*;

public interface AdminManager {

    Provider addProvider(Provider provider);
    Admin addAdmin(Admin admin);
    User addUser(User user);
    void changeStatusOfChange(Long changeId, Long statusId, String description);

    String getZonesList();
    String getNotValidatedServiceChanges();
    String getStatusChangeDictionary();

}
